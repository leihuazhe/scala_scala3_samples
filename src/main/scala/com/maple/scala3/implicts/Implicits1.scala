package com.maple.scala3.implicts

import com.maple.scala3.implicts.ImplicitsDemo.Person

import scala.language.{implicitConversions, postfixOps}

/**
  * Jeff H. Ray
  * June.26th.2022
  */
object ImplicitsDemo:
  case class Person(name: String):
    def greet: String = s"Hey, I'm $name. Scala rocks!"

  //implicit def stringToPerson(string: String): Person = Person(string)

  def main(args: Array[String]): Unit = {
    val greet = "Jeff H. Ray".greet
    println(greet)
  }


given Conversion[String, Person] with
  override def apply(string: String): Person = Person(string)

//given Conversion[Int, Int] with
//  override def apply(int: Int): Int = int


class ImplicitFunction:
  val map = Map("maple" → "枫叶", "apple" → "苹果")

  //def getMap(key: String)(implicit size: Int): Map[String, Int] = {
  def getMap(key: String)(using size: Int): Map[String, Int] = {
    val value = map(key)
    Map(value → size)
  }
