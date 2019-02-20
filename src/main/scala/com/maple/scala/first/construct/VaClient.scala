package com.maple.scala.first.construct

object VaClient {
  private val sources = {
    val map = Map(1 → 2, 2 → 3)
    println("initialize map.")
    map
  }

  def sayHello(): Unit = {
    sources.values.foreach(println)
  }
}

