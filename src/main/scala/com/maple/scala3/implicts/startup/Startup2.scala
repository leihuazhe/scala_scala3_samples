package com.maple.scala3.implicts.startup

//import com.maple.scala3.implicts.{ImplicitFunction, given_Conversion_String_Person,given_Conversion_Int_Int}

import com.maple.scala3.implicts.*

import java.time.LocalDateTime
import scala.language.implicitConversions
import com.maple.scala3.implicts.given_Conversion_LocalDateTime_String
import com.maple.scala3.implicts.given_Conversion_Int_Multiply
import com.maple.scala3.implicts.given_to_rich

import java.sql.Connection

// https://blog.rockthejvm.com/givens-vs-implicits/
object Startup2 {

  def main(args: Array[String]): Unit = {
    testLocalDataTimeConversion(LocalDateTime.now())
    testMultiply()
    //testMaxListUpBound()
  }

  def testLocalDataTimeConversion(dateTime: LocalDateTime) = {
    printStringDateTime(dateTime)
  }

  private def printStringDateTime(dateTime: String) = println(s"Now time is $dateTime")


  def testMultiply(): Unit = {
    val multiply = 3.multiply(Multiply(1, 2))
    println(s"multiply, m:${multiply.m}, n:${multiply.n}")
  }

  def testRichConnection(): Unit = {
    val conn: Connection = null

    val name = "maple"
    val id = 2
    val sql = s"update people set name = ${name} where id = $id"
    conn.executeUpdate(sql)
  }


  //  def testMaxListUpBound(): Unit = {
  //    val implicitParams = new ImplicitsParameter
  //    val list = List(3, 6, 9, 4, 8)
  //
  //    val result = implicitParams.maxListUpBound2(list)
  //  }


}
