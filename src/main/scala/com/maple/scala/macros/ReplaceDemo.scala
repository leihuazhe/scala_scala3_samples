package com.maple.scala.macros

import java.util.Date

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object ReplaceDemo {
    def greeting(person: String): Unit = macro greetingMacro

    def tell(context: String): Unit = macro MacrosImpl.tellMacro

    def greetingMacro(c: blackbox.Context)(person: c.Expr[String]): c.Expr[Unit] = {
        import c.universe._
        println("------------------->  compiling greeting ...")

        reify {
            println("Hello ==> [" + person.splice + ", the time is: " + new Date().toString)
        }
    }

    class MacrosImpl(val c: blackbox.Context) {

        import c.universe._

        def tellMacro(context: c.Tree): c.universe.Tree = {
            println("-------------------> compiling tell macro ...")
            val now = new Date().toString

            q"""
              println("Your input is ["+ $context + "],  time now is"+ $now)
             """
        }
    }

}


