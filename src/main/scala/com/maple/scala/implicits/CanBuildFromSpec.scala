package com.maple.scala.implicits

object CanBuildFromSpec {

  def func1(): Unit = {
    val list = List("1", "2", "3", "4", "5")
    println(list)
  }

  def main(args: Array[String]): Unit = {
    func1()
  }
}
