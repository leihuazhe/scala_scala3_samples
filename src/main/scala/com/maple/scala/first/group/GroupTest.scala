package com.maple.scala.first.group

import scala.io.{BufferedSource, Source}

object GroupTest {

  case class Location(id: Int, province: String, city: String)


  def main(args: Array[String]): Unit = {

    val input: BufferedSource = Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("location.txt"))

    val input1 = input.getLines().map(line ⇒ {
      val info = line.split(",")
      Location(info(0).toInt, info(1), info(2))
    }).toList


    val res = input1.groupBy(_.province).toSeq.sortBy(_._2.head.id).toMap

    res.foreach(x ⇒ println(s"${x._1} -> ${x._2}\n"))

  }

}
