package com.maple.scala3.derive_

import scala.CanEqual.{canEqualEither, derived}
import scala.deriving.*
import scala.compiletime.{erasedValue, summonInline}


inline def summonAll[T <: Tuple]: List[Eq[_]] =
  inline erasedValue[T] match
    case _: EmptyTuple => Nil
    case _: (t *: ts) => summonInline[Eq[t]] :: summonAll[ts]


class All(name: String)
class Person(name: String) extends All(name)
class Boy(name: String) extends Person(name)
class Girl(name: String) extends Person(name)

trait Eq[T]:
  def eqv(x: T, y: T): Boolean
// we need to implement a method Eq.derived on the companion object of Eq that produces a given instance for Eq[T] given a Mirror[T]. Here is a possible implementation

object Eq:
  given Eq[Int] with
    def eqv(x: Int, y: Int) = x == y

  given Eq[Double] with
    def eqv(x: Double, y: Double): Boolean = x.toInt == y.toInt
  //  given  given_Eq_Double2: Eq[Double] with
  //    def eqv(x: Double, y: Double): Boolean = x.toInt != y.toInt

  given Eq[All] with
    def eqv(x: All, y: All) = x == y

  given Eq[Person] with
    def eqv(x: Person, y: Person) =  x == y

  given Eq[Boy] with
    def eqv(x: Boy, y: Boy) =  x == y

  def check(elem: Eq[_])(x: Any, y: Any): Boolean =
    val elemEqs: Eq[Any] = elem.asInstanceOf[Eq[Any]]
    elemEqs.eqv(x, y)

  def iterator[T](p: T) = p.asInstanceOf[Product].productIterator

  def eqSum[T](s: Mirror.SumOf[T], elems: List[Eq[_]]): Eq[T] =
    new Eq[T] :
      def eqv(x: T, y: T): Boolean =
        val ordx = s.ordinal(x) // (3)
        (s.ordinal(y) == ordx) && check(elems(ordx))(x, y) // (4)

  def eqProduct[T](p: Mirror.ProductOf[T], elems: List[Eq[_]]): Eq[T] =
    new Eq[T] :
      def eqv(x: T, y: T): Boolean =
        iterator(x).zip(iterator(y)).zip(elems.iterator).forall { // (5)
          case ((x, y), elem) => check(elem)(x, y)
        }

  // 这个方法为什么定义成 inline given ?
  // 这意味着该方法 will be expanded at call sites （例如，编译器在 具有派生 Eq 子句的 ADT 的伴随对象中生成实例定义），并且如果需要，它可以递归使用，以计算子项的实例
  inline given derived[T](using m: Mirror.Of[T]): Eq[T] =
    lazy val elemInstances = summonAll[
      m.MirroredElemTypes
    ] // materializes the Eq instances for all the child types of type the instance is being derived for. collect the instances as a List
    println(s"elemInstances: ${elemInstances}")
    inline m match // (2)
      case s: Mirror.SumOf[T] => eqSum(s, elemInstances)
      case p: Mirror.ProductOf[T] => eqProduct(p, elemInstances)
// with the instances for children in hand the derived method uses an inline match to dispatch to methods which can construct instances for either sums or products
