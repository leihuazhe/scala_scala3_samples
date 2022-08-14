package com.maple.scala3.derive2


import com.maple.scala3.derive2.`macro`.Log

import scala.compiletime.{erasedValue, summonInline}
import scala.deriving.Mirror

/**
  * EqualDerive
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/08/14 00:20
  */
trait Equal[T]:
  /**
    * 比较 x 和 y 是否相等
    */
  def equal(x: T, y: T): Boolean

//在派生类中提供 Equal 的 Mirror 信息
//通过实现 derived 方法,并生产一个 inline given instance
object Equal:

  given Equal[Int] with
    def equal(x: Int, y: Int) = x == y

  var count = 0


  // derived is defined as an inline given,意味着该方法会在调用时(call sites)进行 扩展 expanded
  inline given derived[T](using m: Mirror.Of[T]): Equal[T] =
    println(s"derived，T: ${Log.describe[T]},MirroredElemTypes: ${Log.describe[m.MirroredElemTypes]}")
    //获取 Mirror 的 Type 的元素类型.
    count = 0
    val elemInstances = summonAll[m.MirroredElemTypes]
    println(s"elemInstances: $elemInstances")
    //cause derived is inline, the match will be resolved at compile-time and only the left-hand side of the matching case will be inlined into the generated code with types refined as revealed by the match
    inline m match
      case s: Mirror.Sum ⇒
        eqSum(s, elemInstances)
      case p: Mirror.Product ⇒
        eqProduct(p, elemInstances)


  def eqSum[T](s: Mirror.SumOf[T], elems: List[Equal[_]]): Equal[T] =
    new Equal[T] :
      override def equal(x: T, y: T): Boolean =
        val ordx: Int = s.ordinal(x)
        val elem: Equal[_] = elems(ordx)
        (ordx == s.ordinal(y)) && check(elem)(x, y)


  def eqProduct[T](s: Mirror.ProductOf[T], elems: List[Equal[_]]): Equal[T] =
    new Equal[T] :
      override def equal(x: T, y: T): Boolean = {
        iterator(x).zip(iterator(y)).zip(elems.iterator).forall {
          case ((x, y), elem) => check(elem)(x, y)
        }
      }


  def check(elem: Equal[_])(x: Any, y: Any): Boolean =
    val res = elem.asInstanceOf[Equal[Any]].equal(x, y)
    println(s"res: $res")
    res

  def iterator[T](p: T) = p.asInstanceOf[Product].productIterator

  //https://stackoverflow.com/questions/65747525/printing-mirroredelemtypes-in-scala-3
  //check T

  //1.(Sm[T] *: (Opt.Nn,EmptyTuple))
  //2.


  inline def summonAll[T <: Tuple]: List[Equal[_]] =
    count += 1
    println(s"count: ${count}, log: " + Log.describe[T])
    inline erasedValue[T] match
      case _: EmptyTuple => Nil
      case x: (t *: ts) =>
        val si = summonInline[Equal[t]]
        println(s"count: ${count}, t: " + Log.describe[t] + s",si: $si")
        si :: summonAll[ts]


end Equal



//summonInline[Equal[t]] =>
//Opt.derived$Equal[T](xxx)

//Opt.derived$Equal[scala.Int](Equal.given_Equal_Int)
//val eqoi = summon[Equal[Opt[Int]]]


