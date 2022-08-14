package com.maple.scala3.derive_macros


import scala.deriving.Mirror
import scala.quoted.*

/**
  * EqualMacros
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/08/14 14:14
  */
trait Equal[T]:
  def equal(x: T, y: T): Boolean

object Equal:
  given Equal[String] with
    def equal(x: String, y: String): Boolean = x == y

  given Equal[Int] with
    def equal(x: Int, y: Int): Boolean = x == y


  // 比较,以下是根据 inline 的实现 signature.
  // inline given derived[T]: (m: Mirror.Of[T]) => Eq[T] = ???
  // 请注意，由于在后续阶段使用 a type ，因此需要通过使用相应的上下文绑定将其提升为 Type 。
  given derived[T: Type](using q: Quotes): Expr[Equal[T]] =
    val ev: Expr[Mirror.Of[T]] = Expr.summon[Mirror.Of[T]].get

    ev match
      case '{  $m: Mirror.ProductOf[T] {type MirroredElemTypes = elementTypes }  } ⇒ {
          val elemInstances: List[Expr[Equal[?]]] = summonAll[elementTypes]
          val eqProductBody: (Expr[T], Expr[T]) => Expr[Boolean] = (x, y) =>
            elemInstances.zipWithIndex.foldLeft(Expr(true: Boolean)) { case (acc, (elem, index)) =>
              val e1 = '{$x.asInstanceOf[Product].productElement(${Expr(index)})}
              val e2 = '{$y.asInstanceOf[Product].productElement($ {Expr(index)})}
              '{$acc && $elem.asInstanceOf[Equal[Any]].equal($e1, $e2)}
            }
          '{eqProduct((x: T, y: T) => $ {eqProductBody('x, 'y)})}
      }
      case '{$m: Mirror.SumOf[T] {type MirroredElemTypes = elementTypes}} => {
            val elemInstances = summonAll[elementTypes]
            val eqSumBody: (Expr[T], Expr[T]) => Expr[Boolean] = (x, y) => {
              val ordx = '{$m.ordinal($x)}
              val ordy = '{$m.ordinal($y)}
              val elements = Expr.ofList(elemInstances)
              '{$ordx == $ordy && $elements($ordx).asInstanceOf[Equal[Any]].equal($x, $y)}
            }
            '{eqSum((x: T, y: T) => $ {eqSumBody('x, 'y)})}
      }





  def eqProduct[T](body: (T, T) => Boolean): Equal[T] =
    new Equal[T] :
      def equal(x: T, y: T): Boolean = body(x, y)

  def eqSum[T](body: (T, T) => Boolean): Equal[T] =
    new Equal[T] :
      def equal(x: T, y: T): Boolean = body(x, y)

  // Shown below is the implementation of summonAll as a macro. We assume that given instances for our primitive types exist.
  def summonAll[T: Type](using Quotes): List[Expr[Equal[_]]] =
    import quotes.reflect.*
    val tpe = TypeRepr.of[T]
    println(s"param tpe(typeRepr):" + tpe.show(using Printer.TypeReprCode))
    //Quote pattern can only match scrutinees of type scala.quoted.Type
    Type.of[T] match
      case '[String *: tpes] ⇒ '{summon[Equal[String]]} :: summonAll[tpes]
      case '[Int *: tpes] => '{ summon[Equal[Int]] } :: summonAll[tpes]
      case '[tpe *: tpes] => derived[tpe] :: summonAll[tpes]
      case '[EmptyTuple] => Nil



end Equal


extension[T] (inline x: T) inline def ===(inline y: T)(using eq: Equal[T]): Boolean = eq.equal(x, y)

inline given eqGen[T]: Equal[T] = $ {Equal.derived[T]}
