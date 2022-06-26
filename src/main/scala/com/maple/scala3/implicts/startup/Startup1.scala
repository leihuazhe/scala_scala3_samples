package com.maple.scala3.implicts.startup

//import com.maple.scala3.implicts.{ImplicitFunction, given_Conversion_String_Person,given_Conversion_Int_Int}

import com.maple.scala3.implicts._
import com.maple.scala3.implicts.given_Conversion_String_Person

import scala.language.implicitConversions

// https://blog.rockthejvm.com/givens-vs-implicits/
object Startup1 {

  def main(args: Array[String]): Unit = {
    testStringToPerson()
    testFunction1()
  }


  def testStringToPerson(): Unit =
    println("Jeff H. Ray".greet)


  def testFunction1(): Unit =
    val function = new ImplicitFunction()

    //implicit val size: Int = 1871
    given Int = 11871
    //val size: Int = 1891


    val value = function.getMap("maple")(using 10)
    val value1 = function.getMap("maple")(using 15)
    val value2 = function.getMap("maple")


    println(s"test1,value is : $value")
    println(s"test1,value1 is : $value1")
    println(s"test1,value1 is : $value2")
}
