package com.maple.scala.second

object HighFunc {

  def inject(arr: Array[Int], initial: Int, operation: (Int, Int) => Int): Int = {
    var carryOver = initial
    arr.foreach(element => carryOver = operation(carryOver, element))
    carryOver
  }

  def main(args: Array[String]): Unit = {
    val envelopes = Array(88, 68, 68, 68, 88, 98, 88, 68, 68, 88, 68, 88, 68, 88, 68, 68, 98, 98, 68, 68, 68, 98, 68, 98, 68, 68, 88, 68, 88, 98)

    envelopes.sum

//    val sum = inject(envelopes, 0, { (carryOver, elem) => carryOver + elem })
    val sum2 = inject(envelopes, 0, _ + _)

    println(sum2)

  }


}
