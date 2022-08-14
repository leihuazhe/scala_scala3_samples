package com.maple.scala3.derive_


import scala.quoted.*
import scala.deriving.Mirror

trait Eq2[T]:
  def eqv(x: T, y: T): Boolean

object Eq2:
  given Eq2[String] with
    def eqv(x: String, y: String) = x == y

  given Eq2[Int] with
    def eqv(x: Int, y: Int) = x == y

  def eqProduct[T](body: (T, T) => Boolean): Eq2[T] =
    new Eq2[T]:
      def eqv(x: T, y: T): Boolean = body(x, y)

  def eqSum[T](body: (T, T) => Boolean): Eq2[T] =
    new Eq2[T]:
      def eqv(x: T, y: T): Boolean = body(x, y)

  // Shown below is the implementation of summonAll as a macro. We assume that given instances for our primitive types exist.
  def summonAll[T: Type](using Quotes): List[Expr[Eq2[_]]] =
    import quotes.reflect.*
    val tpe = TypeRepr.of[T]
    println(s"param tpe(typeRepr):" + tpe.show(using Printer.TypeReprCode))
    Type.of[T] match
      case '[String *: tpes] => '{ summon[Eq2[String]] } :: summonAll[tpes]
      case '[Int *: tpes]    => '{ summon[Eq2[Int]] } :: summonAll[tpes]
      case '[tpe *: tpes]    => derived[tpe] :: summonAll[tpes]
      case '[EmptyTuple]     => Nil

  // 比较,以下是根据 inline 的实现 signature.
  // inline given derived[T]: (m: Mirror.Of[T]) => Eq[T] = ???
  // 请注意，由于在后续阶段使用 a type ，因此需要通过使用相应的上下文绑定将其提升为 Type 。
  given derived[T: Type](using q: Quotes): Expr[Eq2[T]] =
    import quotes.reflect.*
    val ev: Expr[Mirror.Of[T]] = Expr.summon[Mirror.Of[T]].get
    ev match
      case '{ $m: Mirror.ProductOf[T] { type MirroredElemTypes = elementTypes } } =>
        val elemInstances: List[Expr[Eq2[?]]] = summonAll[elementTypes]
        val eqProductBody: (Expr[T], Expr[T]) => Expr[Boolean] = (x, y) =>
          elemInstances.zipWithIndex.foldLeft(Expr(true: Boolean)) { case (acc, (elem, index)) =>
            val e1 = '{ $x.asInstanceOf[Product].productElement(${ Expr(index) }) }
            val e2 = '{ $y.asInstanceOf[Product].productElement(${ Expr(index) }) }

            '{ $acc && $elem.asInstanceOf[Eq2[Any]].eqv($e1, $e2) }
          }
        '{ eqProduct((x: T, y: T) => ${ eqProductBody('x, 'y) }) }

      case '{ $m: Mirror.SumOf[T] { type MirroredElemTypes = elementTypes } } =>
        val elemInstances = summonAll[elementTypes]
        val eqSumBody: (Expr[T], Expr[T]) => Expr[Boolean] = (x, y) =>
          val ordx     = '{ $m.ordinal($x) }
          val ordy     = '{ $m.ordinal($y) }
          val elements = Expr.ofList(elemInstances)
          '{ $ordx == $ordy && $elements($ordx).asInstanceOf[Eq2[Any]].eqv($x, $y) }
        '{ eqSum((x: T, y: T) => ${ eqSumBody('x, 'y) }) }

extension [T](inline x: T) inline def ===(inline y: T)(using eq: Eq2[T]): Boolean = eq.eqv(x, y)

inline given eqGen[T]: Eq2[T] = ${ Eq2.derived[T] }
