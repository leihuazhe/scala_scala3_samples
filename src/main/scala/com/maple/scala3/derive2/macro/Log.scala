package com.maple.scala3.derive2.`macro`

import scala.quoted.*

/**
  * Log
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/08/14 02:08
  */
object Log {

  inline def describe[A]: String = ${describeImpl[A]}

  def describeImpl[T: Type](using Quotes): Expr[String] = {
    import quotes.reflect.*
    Literal(StringConstant(TypeRepr.of[T].dealias.show)).asExprOf[String]
  }
}