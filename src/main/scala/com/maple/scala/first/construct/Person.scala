package com.maple.scala.first.construct

class Person private {

  def say2(): Unit = {
    println("oh oh oh...")
  }

}

object Person {
  val p1= new Person

  def getPerson() = p1
}
