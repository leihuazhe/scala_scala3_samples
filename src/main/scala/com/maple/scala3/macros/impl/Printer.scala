package com.maple.scala3.macros.impl

import scala.quoted.*

/**
  * Printer
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/12 14:56
  */
object Printer:

  inline def printlnUppercase(content:String):Unit =
    ${ printlnUppercaseImpl('content) }

  def printlnUppercaseImpl(content: Expr[String])(using Quotes):Expr[Unit] =
    val expr: Expr[String] = '{$content.toString()}
    '{println("expr: " + $expr)}
    '{println(s"expr: ${$content.toString()}")}


