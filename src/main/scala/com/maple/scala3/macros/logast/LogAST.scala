package com.maple.scala3.macros.logast

import scala.quoted.*

//AST就是把代码通过编译器变成树形的表达形式。

// Macro entry point - an inline function. The argument needs to be also inlined to get
// the information about AST of the code that should be printed not its reference.
inline def logAST[T](inline expression: T) = ${ logASTImpl('expression) }

// An utility to print generic code, both AST and the code itself.
// Useful when creating/matching AST in own macros to see how the 
// valid code looks as AST.
def logASTImpl[T: Type](expression: Expr[T])(using q: Quotes) : Expr[T]= {
  import quotes.reflect.*
  val term = expression.asTerm
  println(s"===========Tree of type ${Type.show}=========:") //com.maple.scala3.macros.Main.Person
  println()
  // new A()
  println(term.show(using Printer.TreeAnsiCode))  //com.maple.scala3.macros.Main.Person.apply("maple", 30, "Soft Programer")
  println()

  //Inlined(None, Nil, Block(Nil, Apply(Select(New(TypeIdent("A")), "<init>"), Nil)))
  //Inlined(None, Nil, Block(Nil, Apply(Select(Ident("Person"), "apply"), List(Literal(StringConstant("maple")), Literal(IntConstant(30)), Literal(StringConstant("Soft Programer"))))))
  println(term.show(using Printer.TreeStructure))
  println()
  println("===========================")
  expression
}

