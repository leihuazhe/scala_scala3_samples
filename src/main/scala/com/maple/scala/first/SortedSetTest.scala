package com.maple.scala.first

import scala.collection.mutable

object SortedSetTest {

  def main(args: Array[String]): Unit = {
    val treeSet = new mutable.LinkedHashSet[String]()

    treeSet += "hbase"
    treeSet += "aegasus"
    treeSet += "pegasus"

    val resList = treeSet.toList :: "maple" :: Nil

    println(resList)

    val res = treeSet.foldLeft(List[String]())((x, y) => y :: x)


    val right1 = treeSet.last

    val right2 = treeSet

    println(right1)
    println(right2)

    println("-----------------")

    treeSet foreach println
    println("-----------------")
    res foreach println
  }
}
