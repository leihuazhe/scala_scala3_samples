package com.maple.scala.third

object CodecTest {

  def main(args: Array[String]): Unit = {
    val input = "maple is maple"

    val res = input.split(" ").toList.take(2)
    println(res)
  }
}
