package com.maple.scala.sort

object SortTest {

  def main(args: Array[String]): Unit = {


    val list = List(("label", 12L, "value"),
      ("label", 12L, "value"),
      ("label", 17L, "value"),
      ("label", 14L, "value"),
      ("label", 10L, "value"),
      ("label", 99L, "value")
    )

    val list1  = List.empty

    val res2 = list.take(4)

    println(res2)


    val res = list.sortWith((x, y) => x._2 > y._2)

    println(list)
    println("==============================")
    println(res)

  }

}
