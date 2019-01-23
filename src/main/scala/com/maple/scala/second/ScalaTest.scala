package com.maple.scala.second

object ScalaTest {

  def main(args: Array[String]): Unit = {
    val envelopes = Array(88, 68, 68, 68, 88, 98, 88, 68, 68, 88, 68, 88, 68, 88, 68, 68, 98, 98, 68, 68, 68, 98, 68, 98, 68, 68, 88, 68, 88, 98)


    val res = func(envelopes, 0, (sum, elem) => sum + elem)

    println(res)

  }

  def func(arr: Array[Int], z: Int, op: (Int, Int) => Int): Int = {
    var result = z
    arr foreach (x => result = op(result, x))

    result
  }

}
