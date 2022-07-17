package com.maple.scala3.macros.impl

import scala.quoted.*

/**
  * AssertMe
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/11 20:35
  */
object MacrosImpl:
  def assertImpl(expr: Expr[Boolean])(using Quotes) =
    val failMsg: Expr[String] = Expr("failed assertion: " + expr.show)
    // access to value failMsg from wrong staging level:
    //[error]    |                        - the definition is at level 0,
    //[error]    |                        - but the access is at level 1.
    //'{ if (!$expr)  throw new AssertionError(s"$failMsg")}
    '{
      if (!$expr) throw new AssertionError($failMsg)
    }

  // ${'[T]} = T
  // '[${T}] = T
//  def dual[T](t T) =
//    ${'[t]} = t
//    '[${t}] = t


//  def dual2[T](t T) =
//  ${'{e}} = e
//  '{${e}} = e


//Expr[T]: 代表类型T的抽象语法树 `{}
//Type[T]: 代表类型T的类型结构   ${}

// T => Expr[T] `{t}
// Expr[T] => T

//引用把 类型T的表达式 转换为类型Expr[T]的表达式, 并且把 类型T 转换为类型 Type[T] 的表达式.

//粘贴把 类型Expr[T]的表达式 转换为 类型T的 表达式, 并贴把类型 Type[T]的表达式 转换为 类型T.