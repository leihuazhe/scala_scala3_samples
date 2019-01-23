package com.maple.scala.first


import java.util.Collections

import scala.Ordering.Byte

object HighToMapTest {

  val names = List("ns1.for", "ns2.bar", "ns2.baz", "froznit")
  val namespace = List("ns1", "ns2")

  def findNamespace(n: String): Option[String] = namespace.find(n.startsWith)

  val groupNames = names.groupBy(findNamespace).collect {
    case (Some(ns), name) ⇒ (ns, name)
  }

  def samples1(): Unit = {
    val list = List("this", "maps", "string", "to", "length")
    val map: Map[String, Int] = list.foldLeft(Map[String, Int]()) { (m, s) ⇒ m + (s → s.length) }


  }

  def main(args: Array[String]): Unit = {
    val test = List("2018-12-01", "2018-12-02", "2018-12-03", "2018-12-04", "2018-12-05", "2018-12-06", "2018-12-07",
      "2018-12-08", "2018-12-19", "2018-12-18", "2018-12-17", "2018-12-14", "2018-12-23", "2018-12-79", "2018-12-27",
      "2018-12-11", "2018-12-21")

    val res = test.sorted

    println(res)
  }
}
