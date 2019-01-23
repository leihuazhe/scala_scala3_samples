package com.maple.scala.first

object MapTest {

  def main(args: Array[String]): Unit = {

    var map1 = Map("1" → "2", "3" → "4")
    val map2 = Map("5" → "6", "7" → "8")


    val res = map1 ++= map2

  }
}
