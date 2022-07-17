package com.maple.scala3.macros.impl

import com.maple.scala3.macros.impl.*

import scala.quoted.*

/**
  * AssertMe
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/11 20:35
  */
object Macros:
  // 'expr 是 boolean 表达式树
  inline def assert(expr: => Boolean):Unit =
    ${ assertImpl('expr) }

  def assertImpl(expr: Expr[Boolean])(using Quotes) =
    val failMsg: Expr[String] = Expr("failed assertion: " + expr.show)
    '{ if (!$expr) throw new AssertionError($failMsg) }


// e 是一个表达式, 则 '{e} 表示 e 的类型化的抽象语法树(the typed abstract syntax tree)
//