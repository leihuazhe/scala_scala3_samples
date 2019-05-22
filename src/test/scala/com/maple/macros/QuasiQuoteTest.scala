package com.maple.macros

import com.maple.scala.macros.QuasiQuoteSpec

import scala.language.experimental.macros

object QuasiQuoteTest extends App {

    val ruleIds = List("1", "2", "3").map(x ⇒ s""""$x"""")



    val ruleIdStr = ruleIds.mkString(",")

    println(ruleIdStr)

    //    QuasiQuoteSpec.tell("你好，大佬")

}


