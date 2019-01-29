package com.maple.scala.first.construct

object ConstructMain {

  def main(args: Array[String]): Unit = {
    //    val person = new Person
    val person = Person.getPerson()
    person.say2()
  }

}
