package com.maple.scala.second

import java.util.concurrent.TimeUnit

object AnonymousFunc {

  def main(args: Array[String]): Unit = {

    val anonyFuc = (name: String) ⇒ {
      println(s"your name is $name")
    }

    anonyFuc("maple")


 val res =  TimeUnit.MINUTES.toMillis(3)
    println(res)

  }

}
