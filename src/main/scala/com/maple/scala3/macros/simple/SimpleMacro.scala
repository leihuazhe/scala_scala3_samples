package com.maple.scala3.macros.simple;

import scala.quoted.*

// Very simple macros examples, just to see quotation and splicing.
object SimpleMacro {

        inline def printlnForConstant() : Unit = ${ printlnForConstantImpl }

        // Return expression to print a constant, only implicit Quotes is needed.
        def printlnForConstantImpl(using q: Quotes) : Expr[Unit] = {
                '{ println("u1") }
        }

        // Use splicing to include generated parameter.
        def printlnUppercaseImpl(str: Expr[String])(using q: Quotes) : Expr[Unit] = {
        val expr: Expr[String] = '{ $str.toUpperCase }
        '{ println($expr) }
        }

        inline def printlnUppercase(str: String) : Unit = ${ printlnUppercaseImpl('str) }

}
