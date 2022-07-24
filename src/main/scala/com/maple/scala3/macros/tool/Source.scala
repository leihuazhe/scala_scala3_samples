package com.maple.scala3.macros.tool

import scala.quoted.*

/** Source
  *
  * @author
  *   leihz
  * @version 1.0.0
  * @since 2022/07/24
  *   00:01
  */
object Source:
  inline def line(): Int = ${ lineImpl() }

  def lineImpl()(using Quotes) =
    import quotes.reflect.*
    val pos = Position.ofMacroExpansion
    Expr(pos.startLine + 1)

  inline def reify(inline a: Any) = ${ reifyImpl('a) }

  def reifyImpl(a: Expr[Any])(using Quotes): Expr[String] = {
    import quotes.reflect.*
    Literal(StringConstant(Printer.TreeStructure.show(a.asTerm))).asExprOf[String]
  }
