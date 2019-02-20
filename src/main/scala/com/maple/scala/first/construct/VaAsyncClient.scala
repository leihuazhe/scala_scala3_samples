package com.maple.scala.first.construct

object VaAsyncClient {
  private val sources = {
    val map = Map(1 → 2, 2 → 3)
    println("initialize VaAsyncClient map.")
    map
  }

  def sayHello(): Unit = {
    sources.values.foreach(println)
  }
}

