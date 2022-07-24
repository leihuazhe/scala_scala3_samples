package com.maple.scala3.macros

import com.maple.scala3.macros.impl._
import com.maple.scala3.macros.impl.MainImpl._

import scala.quoted.Quotes
import com.maple.scala3.macros.logast._
import com.maple.scala3.macros.tool._
//import org.scalafmt.config.DanglingParentheses.Exclude.`class`

/** Main
  *
  * @author
  *   leihz
  * @version 1.0.0
  * @since 2022/07/12
  *   10:09
  */

@main def testIsCaseClass() =
  val result = PrintTree.isCaseClass[Person]
  println(s"is case class result for person: $result")

  class Maple {}

  val result1 = PrintTree.isCaseClass[Maple]
  println(s"is case class result for maple : $result1")

@main def printExpression() =
  val t  = List(1, 3, 5)
  val t1 = inspect(t)
  println(s"t1: $t1")
  PrintTree.printTree(t1)

@main def showTree() =
  val person = Person("maple", 30, "R&D")
  val trees  = PrintTree.showTree(person)
  println(s"runtime trees: $trees")

@main def printTree() =
  // usage
  PrintTree.printTree { (s: String) =>
    s.length
  }
  PrintTree.printTree {
    def hello(str: String): String = {
      s"hello: $str"
    }
  }

//  inspect(sys error "abort")

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

  // Inlined(None, Nil, Block(Nil, Block(List(DefDef("$anonfun", List(TermParamClause(List(ValDef("s", TypeIdent("String"), None)))), Inferred(), Some(Block(Nil, Apply(Select(Ident("s"), "length"), Nil))))), Closure(Ident("$anonfun"), None))))
