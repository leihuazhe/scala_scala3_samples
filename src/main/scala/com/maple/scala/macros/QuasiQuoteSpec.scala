package com.maple.scala.macros

import java.util.Date

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object QuasiQuoteSpec {

    def tell(context: String): Hello = macro generateCaseClassResultSetMapper

    def generateCaseClassResultSetMapper(c: blackbox.Context)(context: c.Tree): c.universe.Tree = {
        import c.universe._

        val list = List("I", "love", "YT")

        val tree =
            q"""
         import wangzx.scala_commons.sql._
         import java.sql.ResultSet

         new Hello {

          override def hello(): String = {
            println( ..$list )
          }
         }
       """
        println(tree)
        tree
    }

}

trait Hello {

    def hello(): String
}
