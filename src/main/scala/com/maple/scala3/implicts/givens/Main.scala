package com.maple.scala3.implicts.givens


@main def testOrd(): Unit = {

  println(s"Max is ${max(1, 6)}")


  val list1 = List(11, 3)
  val list2 = List(6, 9)
  val maxValue = max(list1, list2)
  println(s"Max is $maxValue")


}

def max[T](a: T, b: T)(using ord: Ord[T]) =
  if ord.compare(a, b) > 0 then a else b


@main def testClassContext(): Unit = {
  val b = GivenIntBox(using 23)
  println(b)
  import b.given
  val x = summon[Int]
  println(x)
}


