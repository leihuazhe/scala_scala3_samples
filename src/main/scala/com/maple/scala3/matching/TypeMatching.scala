package com.maple.scala3.matching

/**
  * TypeMatching
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/14 20:50
  */
object TypeMatching {

  type Elem[X] = X match
    case String => Char
    case Array[t] => t
    case Iterable[t] => t

  /**
    * @tparam X
    */
  //将左侧的定义 reduce 为 右侧
  // String => Char
  // Array[t] => LeafElem[t]

  //Match types can form part of recursive type definitions.
  type LeafElem[X] = X match
    case String => Char
    case Array[t] => LeafElem[t]
    case Iterable[t] => LeafElem[t]
    case AnyVal => X

  //Recursive match type definitions can also be given an upper bound, like this:
  type Concat[Xs <: Tuple, +Ys <: Tuple] <: Tuple = Xs match
    case EmptyTuple => Ys
    case x *: xs => x *: Concat[xs, Ys]

  //  Elem[String] =:= Char
  //  Elem[Array[Int]] =:= Int
  //  Elem[List[Float]] =:= Float
  //  Elem[Nil.type] =:= Nothing

  @main def testFetchElement(): Unit = {
    fetchElement('A')
  }

  //把普通类型转换为 LeafElem[X]
  def leafElem[X](x: X): LeafElem[X] = x match {
    case x: String ⇒ x.charAt(0)
    case x: Array[t] => leafElem(x(0))
    case x: Iterable[t] => leafElem(x.head)
    case x: AnyVal => x
  }



  @main def testLeafElem(): Unit = {
    // LeafElem[List[String]] => LeafElem[String] => Char
    val value: LeafElem[List[String]] = leafElem(List("1", "2", "3", "4"))
    println(s"value: $value")
    //LeafElem[Array[String]] => LeafElem[String] => Char
    val value2: LeafElem[Array[String]] = leafElem(Array("5", "7", "9"))
    println(s"value: $value2")

    // LeafElem[String] => Char
    val value3: LeafElem[String] = leafElem("我是谁")
    println(s"value: $value3")
    // LeafElem[Int] => Int
    val value4: LeafElem[Int] = leafElem(6)
    println(s"value: $value4")
  }


  def fetchElement(context: Elem[String]): Unit = {
    val clazz = context.getClass
    println(s"clazz: $clazz")
  }

}



