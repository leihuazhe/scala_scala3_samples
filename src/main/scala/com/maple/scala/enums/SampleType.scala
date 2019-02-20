package com.maple.scala.enums

import scala.collection.immutable
import scala.util.matching.Regex

object SampleType extends Enumeration {
  type SampleType = Value

  val Redis = Value(1, "redis")
  val Pegasus = Value(2, "pegasus")
  val HBase = Value(3, "hbase")
  val None = Value(4, "none")
}

object Extension {

  implicit class PathExtractor(sc: StringContext) {
    object path {
      def unapplySeq(str: String): Option[Seq[String]] = {
        sc.parts.map(Regex.quote).mkString("^", "([^/]+)", "$").r.unapplySeq(str)
      }
    }

  }

}

object Main extends App {

  import com.maple.scala.enums.Extension._


  val cfcq = s"cf-cq"

  cfcq match {
    case path"$cf-$cq" => println(cf + cq)
  }


  val res = SampleType.Redis eq SampleType.HBase
  val res1 = SampleType.HBase equals SampleType.HBase

  println(s"result res =========>  $res")
  println(s"result res1=========>  $res1")

  val strs = Map[String, String]("1" -> "1", "2" -> "2")

  val res3: immutable.Iterable[String] = strs.map { str =>
    s"${str._1}:${str._2}"
  }.toList


}