package com.maple.scala3.macros

import com.maple.scala3.macros.impl._
import scala.quoted.Quotes
import com.maple.scala3.macros.logast._

/**
  * Main
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/12 10:09
  */
object Main:

  /*@main def program2 = '{
  val x = 1
  ${ Macros.assertImpl('{ x != 0 }) }
  }*/

  // Splice ${...} outside quotes '{...} or inline method
  @main def program() =
    val x = 1
    Macros.assert(x == 0)

  @main def print() =
    Printer.printlnUppercase("My name is Jeffrey, aka Maple.")


  case class Person(name: String, age: Int, job: String)

  @main def ast() =
    // Logged AST that was used to find out the structure of the code that was matched in the macro.
    val p: Person = logAST {
      Person("maple", 30, "Soft Programer")
    }