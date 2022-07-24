package com.maple.scala3.turtoral

import com.maple.scala3.turtoral.extensions._
import scala.meta.internal.javacp.BaseType.S

@main
def x1() = {}

@main
def test2() = {
  val str    = "Maple"
  val newStr = str.customUpper
  println("newStr: $newStr")
}

def matchCase(color: Color) = {

//  println("color: $color.")

  color.match {
    case Color.Blue  => println(s"blue: $color")
    case Color.Green => println(s"green: $color")
    case Color.Red   => println(s"red: $color")
  }
}

def matchArray(list: List[String]) = {}

object T {

  def matchCase(color: Color) = {
    val res = color.ordinal

  }

}
