package com.maple.scala.first

object MapTest {

  def main(args: Array[String]): Unit = {
    val words = Some("hello I am Maple and you I am youjie yes")

    val res = words.map(l ⇒ l.split(" ")).groupBy(identity)



  }


  def func1(): Unit = {
    val map1 = Map("1" → "2", "3" → "4")
    val map2 = Map("5" → "6", "7" → "8")
    val res = map1 ++ map2

    res.foreach(println)

    //
    val list1 = List("1", "2", "3")
    val res1 = list1 ++: "9999"


    res1.foreach(println)
  }
}
