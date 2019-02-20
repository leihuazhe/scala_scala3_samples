package com.maple.scala


import com.maple.ByteTest

import scala.util.matching.Regex


object Extension {

  implicit class PathExtractor(sc: StringContext) {

    object path {
      def unapplySeq(str: String): Option[Seq[String]] = {
        sc.parts.map(Regex.quote).mkString("^", "([^/]+)", "$").r.unapplySeq(str)
      }
    }

  }

}

object InterpolatorsExtractors {


  def main(args: Array[String]): Unit = {
    import com.maple.scala.Extension._

    val cfcq = s"cf-cq"

    cfcq match {
      case path"$_-$cq" => println(s"$cq")
    }


    val result = List("xx-987654321", "xx-12132132", "interest_finance_score", "intere-st_game_kidssmart_score", "123-sxs")


    val res = result.map {
      r =>
        r match {
          case path"$_-$ts" if ByteTest.isNumeric(ts) => {
            println("access 1")
            true
          }
          case _ => {
            println("access 2")
            false
          }
        }
    }

    println(res)


  }

}


