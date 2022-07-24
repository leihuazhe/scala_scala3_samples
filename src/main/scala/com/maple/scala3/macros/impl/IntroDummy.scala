package com.maple.scala3.macros.impl

import scala.quoted.*
import scala.annotation.compileTimeOnly
import scala.meta.internal.javacp.BaseType.S

object Source2:
  inline def lines_bad(inline xs: List[String]): List[String] = ${ lines_badImpl('{ xs }) }

  def lines_badImpl(xs: Expr[List[String]])(using Quotes): Expr[List[String]] =
    import quotes.reflect.*
    val dummySym = Symbol.requiredModule("com.maple.scala3.macros.impl.Dummy")
    xs match
      case ListApply(args) =>
        val args2 = args map { arg =>
          val term = arg.asTerm

          // Apply(Select(Ident(Dummy),echo),List(Literal(Constant(bar))))
          println(s"arg: ${term.show(using Printer.TreeCode)}")
          term match
            case a @ Apply(
                  Select(qual, "echo"),
                  List(Literal(StringConstant(str)))
                ) // Dummy.echo("bar")
                if qual.symbol == dummySym =>
              val pos = a.pos
              Expr(s"${pos.startLine + 1}: $str")
            case _ => arg
        }
        '{ List(${ Varargs[String](args2.toList) }: _*) }

  inline def lines(inline xs: List[String]) = ${ linesImpl('xs) }

  def linesImpl(xs: Expr[List[String]])(using Quotes) =
    import quotes.reflect.*
    xs match {
      case '{ List[String]($vargs*) } =>
        val args2 = vargs match {
          case Varargs(args) =>
            args map { arg =>
              arg match {
                case '{ Dummy.echo($str) } =>
                  val pos = arg.asTerm.pos
                  Expr(s"${pos.startLine + 1}: ${str.valueOrAbort}")
                case _ => arg
              }
            }
        }
        '{ List(${ Varargs[String](args2.toList) }: _*) }
    }

  // bad example. see below for quoted pattern.
  object ListApply:
    def unapply(expr: Expr[List[String]])(using Quotes): Option[Seq[Expr[String]]] =
      import quotes.reflect.*
      def rec(tree: Term): Option[Seq[Expr[String]]] =
        // scala.List.apply[java.lang.String]("foo", com.maple.scala3.macros.impl.Dummy.echo("bar"))
        println(tree.show(using Printer.TreeCode))
        tree match
          case Inlined(_, _, e) =>
            rec(e) // expansion -> TreesApply
          case Block(Nil, e) =>
            rec(e)
          case Typed(e, _) =>
            rec(e) // apply(expr: Term, tpt: TypeTree)
          // Apply(fun: Term, args: List[Term])
          case Apply(TypeApply(Select(obj, "apply"), _), List(e)) if obj.symbol.name == "List" =>
            rec(e)
          // apply(elems: List[Term], tpt: TypeTree)
          case Repeated(elems, _) =>
            Some(elems.map(_.asExprOf[String]))
      rec(expr.asTerm)
  end ListApply

end Source2

object Dummy:
  @compileTimeOnly("echo can only be used in lines macro")
  def echo(line: String): String = ???
end Dummy
// given ApplyTypeTest: TypeTest[Tree, Apply]
