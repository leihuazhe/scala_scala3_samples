package com.maple.scala3.macros.tool

import scala.quoted.*
import dotty.tools.dotc.core.Types.TypeRef.apply

// macro
object PrintTree:

  inline def showTree[T](inline x: T): String = ${ showTreeImpl[T]('x) }

  def showTreeImpl[T](x: Expr[T])(using Quotes): Expr[String] =
    import quotes.reflect.*
    Expr(x.asTerm.show)

  inline def printTree[T](inline x: T): Unit = ${ printTreeImpl('x) }

  def printTreeImpl[T: Type](x: Expr[T])(using qctx: Quotes): Expr[Unit] =
    import qctx.reflect.*
    val term = x.asTerm
    val tpe  = term.tpe
    println("PrintTree: " + x.asTerm.show(using Printer.TreeStructure))
    '{ () }

  // 判断 class 是否为 case class
  inline def isCaseClass[T]: Boolean = ${ isCaseClassImpl[T] }

  // Person: isClassDef: true, isCase: true
  // Maple: isClassDef: true, isCase: false
  // def isCaseClassImpl[T: Type](using Quotes): Expr[Boolean] = {
  def isCaseClassImpl[T: Type](using Quotes): Expr[Boolean] =
    import quotes.reflect.*
    val repr       = TypeRepr.of[T]
    val sym        = repr.typeSymbol
    val isClassDef = sym.isClassDef
    val isCase     = sym.flags.is(Flags.Case)
    println(s"${sym.name}: isClassDef: $isClassDef, isCase: $isCase")
    Expr.apply(isClassDef && isCase)
end PrintTree
// usage
// printTree {
//   (s: String) => s.length
// }

// output
//Inlined(None, Nil, Block(Nil, Block(List(DefDef("$anonfun", List(TermParamClause(List(ValDef("s", TypeIdent("String"), None)))), Inferred(), Some(Block(Nil, Apply(Select(Ident("s"), "length"), Nil))))), Closure(Ident("$anonfun"), None))))

// after a while the above representation becomes semi-readable
