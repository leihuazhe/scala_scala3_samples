package com.maple.scala.first

object MapTest {

  def main(args: Array[String]): Unit = {

    var map1 = Map("1" → "2", "3" → "4")
    val map2 = Map("5" → "6", "7" → "8")

    Map(1 -> 1, 2 -> 2).foreach(((x: Int, y: Int) => {
      println(s"$x----$y")
    }).tupled)


    val res = map1 ++= map2

  }
}
