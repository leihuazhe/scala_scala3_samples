//package com.maple.scala.xiaomi
//
////case class Person(name: String, age: Option[Int] = None)
//
///*
//  这个主题是关于产品和元组最常见的子类。
//
//我应该在这篇文章前面说我不打算尝试任何学术风格的讨论，而是试着用实际的术语解释产品并远离理论。
// 也就是说，可以将 Product 视为（可能）异构集合（不可调整大小的集合）。
// 有几个 Product 类（Product，Product1，Product2 ......）。
//  所有Product类都扩展Product，其中包含常用方法。 Product具有访问Product成员的方法，子类添加有关Members的类型信息。
//
//考虑产品的一个好方法是看看作为产品的元组。 有一个很好的堆栈溢出问题值得关注。
//
//为了给出更多上下文，请考虑其他众所周知的Product子类：
//
//1.All case classes
//2.List
//3.Option
//
//
//
//
//
//
//  */
//object Tips1Product {
//
//  def test1(): Unit = {
//    val p = Person("Lucky", Option(18))
//
//    println(p.productArity) //元素的个数            输出：2
//    println(p.productElement(0)) //第1个元素         输出：Lucky
//    println(p.productElement(1)) //第二个元素        输出：Some(18)
//    p.productIterator foreach println //便利         输出：Lucky  Some(18)
//
//  }
//
//  def test2(): Unit = {
//    val product: Product = (1, '2', 3)
//    product.productIterator foreach println
//
//    val prefix = product.productPrefix
//    println(prefix)
//  }
//
//
//  /**
//    * Tuple 的父类 Product
//    */
//  def test3(): Unit = {
//    val msg = false
//
//    val result: Product = msg match {
//      case true ⇒ (1, 2)
//      case false ⇒ (1, 2, 3)
//    }
//
//
//  }
//
//
//  def main(args: Array[String]): Unit = {
//
//
//  }
//
//}
