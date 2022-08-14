//package com.maple.scala3.reflection.impl
//
//import scala.quoted.*
//
//object TypeReprExp:
//
//  inline def toG[T]() = {
//    ${ g[T] }
//  }
//
//  def g[T: Type](using Quotes) =
//    import quotes.reflect.*
//    val tpe: TypeRepr = TypeRepr.of[T]
//    println(s"TypeReprExp tpe: ${tpe}")
//    val type1 = tpe.asType
//    println(s"TypeReprExp type1: ${type1}")
//
//    tpe.asType match
//      case '[t] => '{ println() }
