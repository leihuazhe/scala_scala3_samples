package com.maple.scala3.macros.impl

import scala.quoted.*
import com.maple.scala3.macros.tool.Source
import scala.meta.internal.javacp.BaseType.S
import fansi.Str
import scala.annotation.compileTimeOnly
// https://docs.scala-lang.org/scala3/guides/macros/macros.html#macros-and-type-parameters
object MainImpl:

  // The following macro implementation simply prints the expression of the provided argument:
  // 需要有个入口类
  def inspectCode(x: Expr[Any])(using Quotes): Expr[Any] =
    println(s"inspectCode ${x.show}")
    x

  // As foreshadowed in the section on Inline, inline methods provide the entry point for macro definitions:
  // What is evidence$1
  inline def inspect(inline x: Any): Any = ${ inspectCode('x) }

  // Macros and Type Parameters
  // Expr[T] represents a Scala expression of type T.
  // Type[T] to respresent the Scala type T.
  inline def logged[T](inline x: T): T = ${ loggedCode('x) }

  // Both the instance of Type[T] and the contextual Quotes are automatically provided by the splice
  // in the corresponding inline method (that is, logged) and can be used by the macro implementation.
  def loggedCode[T](expr: Expr[T])(using Type[T], Quotes): Expr[T] = ???

  // Defining and Using Macros
  // A key difference between inlining and macros is the way they are evaluated.
  // Inlining works by rewriting code and performing optimisations based on rules the compiler knows.
  // On the other hand, a macro executes user-written code that generates the code that the macro expands to.
  // inline 编译器自己去优化代码, Macro 是通过用户编写的代码来扩展。

  // We will define a macro that compute xⁿ for statically known value x and n.
  //
//   inline def power(x: Double, inline n: Int): Double =
//     inline if n == 0 then 0
//     else inline if n % 2 == 1 then x * power(x, n - 1)
//     else power(x * x, n / 2)

  inline def power(inline x: Double, inline n: Int) = ${ powerCode('x, 'n) }

  // command + alt + v 补全返回值 refractor.extract.variable
  def powerCode(x: Expr[Double], n: Expr[Int])(using Quotes): Expr[Double] =
    // how we create and look into the Exprs.
    // val value: Double = pow(${ x }, ${ n })
    val value: Double = pow(x.valueOrAbort, n.valueOrAbort)
    // '{ value }
    Expr(value)

  def pow(x: Double, n: Int): Double =
    if n == 0 then 1 else x * pow(x, n - 1)

  // Add one in compiler time.
  inline def addOne(inline x: Int) = { ${ addOneImpl('x) } }

  def addOneImpl(x: Expr[Int])(using Quotes): Expr[Int] = {
    Expr(x.valueOrAbort + 1)
  }

  // This looks too verbose without much benefit.
  def addOneBadImpl(x: Expr[Int])(using Quotes): Expr[Int] = {
    import quotes.reflect.*
    val term = x.asTerm
    println(s"addOneBadImpl: $term")
    term match {

      // call,bindlings,expansion.
      case Inlined(_, _, Literal(IntConstant(n))) =>
        Literal(IntConstant(n + 1)).asExprOf[Int]
    }
  }

  // def value[T](using FromExpr[T]): Option[T] = {
  //   given Quotes = Quotes.this
  //   summon[FromExpr[T]].unapply(self)
  // }
  inline def addOneList(inline x: Int): List[Int] = ${ addOneListImpl('x) }

  private def addOneListImpl(x: Expr[Int])(using Quotes) = {
    import quotes.reflect.*
    val inner = Expr(x.valueOrError + 1)
    '{ List($inner) }

    // Expr(List(x.valueOrAbort + 1))
  }

  inline def oneToString(inline x: Int): String = ${ oneToStringImpl('x) }

  def oneToStringImpl(x: Expr[Int])(using Quotes): Expr[String] = {
    import quotes.reflect.*
    val inner = Literal(IntConstant(x.valueOrAbort + 1))
    Select.unique(inner, "toString").appliedToNone.asExprOf[String]
    // appliedToNone 是调用空方法.
  }

  // === ValDef ===
  inline def addOneX(inline x: Int): Int = ${ addOneXImpl('{ x }) }

  def addOneXImpl(x: Expr[Int])(using Quotes): Expr[Int] =
    val rhs = Expr(x.valueOrError + 1)

    // return a reference to it using quotes as follows
    '{
      val x = $rhs
      x
    }
  inline def addOneXv2(inline x: Int): Int = ${ addOneXv2Impl('{ x }) }

  def addOneXv2Impl(x: Expr[Int])(using Quotes): Expr[Int] = {

    import quotes.reflect.*
    val rhs = Expr(x.valueOrAbort + 1)
    // 20
    println("rhs.show: " + rhs.asTerm.show(using Printer.TreeCode))
    val sym = Symbol.newVal(
      Symbol.spliceOwner,
      "x",
      TypeRepr.of[Int], // scala.Int
      Flags.EmptyFlags,
      Symbol.noSymbol
    )
    // val x: scala.Int
    println("sym.show: " + sym.tree.show(using Printer.TreeCode))
    val vd = ValDef(sym, Some(rhs.asTerm))
    // val x: scala.Int = 20
    println("vd.show: " + vd.show(using Printer.TreeCode))
    // {
    //  val x: scala.Int = 20
    //  x
    // }
    val ret = Block(List(vd), Ref(sym)).asExprOf[Int]

    // 用这个可以打出源码
    println("addOneXv2Impl: " + ret.asTerm.show(using Printer.TreeCode))
    println("addOneXv2Impl: " + ret.asTerm.show(using Printer.TreeStructure))

    println(s"addOneXv2Impl: ${Source.reify(ret)}")
    ret
  }

  // lazy val Compile = config => lazy val Compile = Config("Compile")
  case class Config(name: String)
  inline def config: Config = ${ configImpl }

  def configImpl(using Quotes): Expr[Config] = {
    import quotes.reflect.*
    def enclosingTerm(sym: Symbol): Symbol = {
      println("sym tree: " + sym.tree.show(using Printer.TreeCode))
      println(s"sym.flags is: ${sym.flags}")
      sym match {
        case sym if sym.flags is Flags.Macro => enclosingTerm(sym.owner)
        case sym if !sym.isTerm              => enclosingTerm(sym.owner)
        case _                               => sym
      }
    }
    val n     = enclosingTerm(Symbol.spliceOwner).name
    val nExpr = Expr(n)
    '{ Config($nExpr) }
  }
//Above:
//Symbol.spliceOwner: this gives reference to the defintion where the macro expansion happened.

  // TypeRepr的用处
  inline def typeEq[A1, A2]: Boolean = ${ typeEqImpl[A1, A2] }

  def typeEqImpl[A1: Type, A2: Type](using Quotes): Expr[Boolean] =
    import quotes.reflect.*
    Expr(TypeRepr.of[A1] =:= TypeRepr.of[A2])

//AppliedType 的使用
  inline def paramInfo[A]: List[String] = ${ paramInfoImpl[A] }

  def paramInfoImpl[A: Type](using Quotes) = {
    import quotes.reflect.*
    val tpe = TypeRepr.of[A]
    println(s"param tpe(typeRepr):" + tpe.show(using Printer.TypeReprCode))
    // Widen from TermRef to its underlying non-termref base type, while also skipping ByName types.
    val what = tpe.widenTermRefByName.dealias
    println(s"param what:" + what.show(using Printer.TypeReprCode))
    val tags = what match {
      case AppliedType(_, args) => args
      case _                    => Nil
    }
    Expr(tags.map(_.show))
  }

  //
  inline def right[A](inline a: A, inline which: String) = ${ rightImpl('a, 'which) }

  def rightImpl[A: Type](a: Expr[A], which: Expr[String])(using Quotes) = {
    import quotes.reflect.*
    val w = which.valueOrAbort
    val leftType = w match
      case "String"       => TypeRepr.of[String]
      case "List[String]" => TypeRepr.of[List[String]]

    val msg = w match
      case "String"       => Expr("empty not allowed")
      case "List[String]" => Expr(List("empty not allowed"))

    leftType.asType match {
      case '[l] =>
        '{
          val e0: Either[l, A] = Right[l, A]($a)
          val e1 = e0 flatMap { x =>
            if x == null.asInstanceOf[A] then Left[l, A]($msg.asInstanceOf[l])
            else Right(x)
          }
          e1.toString
        }
    }
  }

  // lambda
  /*
      val f: Int => A = (p0: Int) => {
        ....
      }
   */
  inline def mkLambda[A](inline a: A): A = ${ mkLambdaImpl[A]('{ a }) }

  def mkLambdaImpl[A: Type](a: Expr[A])(using Quotes): Expr[A] =
    import quotes.reflect.*

    val lambdaTpe =
      // paramNames: List[String]
      MethodType(List("p0"))(_ => List(TypeRepr.of[Int]), _ => TypeRepr.of[A])
    val lambda = Lambda(
      owner = Symbol.spliceOwner,
      tpe = lambdaTpe,
      rhsFn = (sym, params) => {
        val p0 = params.head.asInstanceOf[Term]
        a.asTerm.changeOwner(sym)
      }
    )

    /** ((p0: Int) => 3) ((p0: Int) => { val x: Int = 3 x.*(x)./(2).+(1) })
      */
    println("lambda: " + lambda.show(using Printer.TreeCode))

    '{
      val f: Int => A = ${ lambda.asExprOf[Int => A] }
      f(1)
    }

  inline def <<=[A](inline a: A): Option[A] =
    compiletime.error("<<= is removed; migrated to := instead")

  inline def removedMethod[A](a: A): Option[A] = ${ removedMethodImpl('a) }

  def removedMethodImpl[A: Type](a: Expr[A])(using qctx: Quotes): Expr[Option[A]] =
    import qctx.reflect.*
    report.errorAndAbort("当前方法已被移除,请不要使用")
