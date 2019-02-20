package com.maple.scala.first.construct

object ConstructMain {

  def main(args: Array[String]): Unit = {
    println("main start ...")
    VaClient.sayHello()
    VaClient.sayHello()
    VaClient.sayHello()

    println("main stop ...")


    val person = Person.getPerson()
    person.say2()
  }

}
