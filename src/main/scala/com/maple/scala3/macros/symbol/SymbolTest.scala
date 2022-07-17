package com.maple.scala3.macros.symbol

import com.maple.scala3.macros.logast._

@main def symbolTest() = {

  def printSymbols = Symbols.printSymbols()

  // It will print: Splice owner: macro, parent printSymbols, grandParent symbolTest
  printSymbols()

  // To find how the function definition should be constructed in the macro.
  logAST {
    def f() = {
      println("f()")
    }
  }
}
