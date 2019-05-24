package com.maple.scala.macros.lw

import java.util.Date

import scala.language.experimental.macros
import scala.reflect.macros.blackbox

object GenericRow {

    def genericToRow[T: GenericRow](c: T): Map[String, Any] = implicitly[GenericRow[T]].toRow(c)

    implicit def toRow[T]: GenericRow[T] = macro genericRow[T]

    def greeting[T](person: String): Unit = macro greetingMacro[T]

    def genericRow[T: c.WeakTypeTag](c: blackbox.Context): c.Tree = {
        import c.universe._
        println("------------------->  compiling genericRow ...")
        //        val t: c.WeakTypeTag[T] = implicitly[c.WeakTypeTag[T]]

        val tpe = weakTypeOf[T]

        assert(tpe.typeSymbol.asClass.isCaseClass, s"only support CaseClass, but ${tpe.typeSymbol.fullName} is not")

        val companion = tpe.typeSymbol.asClass.companion

        val constructor: c.universe.MethodSymbol = tpe.typeSymbol.asClass.primaryConstructor.asMethod

        var index = 0
        val args: List[c.Tree] = constructor.paramLists.head.map { p: c.universe.Symbol =>
            val term: c.universe.TermSymbol = p.asTerm
            index += 1
            val name = term.name.toTermName
            val newTerm = name.decodedName.toString

            if (term.isParamWithDefault) {
                val defMethod: c.universe.Symbol = tpe.typeSymbol.asClass.companion.asModule.typeSignature.member(TermName("$lessinit$greater$default$" + index))

                q"""$newTerm -> $companion.$defMethod"""
            } else
                q"""$newTerm -> t.$name"""
        }

        val tree =
            q"""
           new GenericRow[$tpe] {

              override def toRow(t: $tpe): Map[String, Any] = Map(..$args)
           }
       """
        println(tree)

        tree
    }

    def greetingMacro[T: c.WeakTypeTag](c: blackbox.Context)(person: c.Expr[String]): c.Expr[Unit] = {
        import c.universe._
        println("------------------->  compiling greeting ...")

        val t: c.WeakTypeTag[T] = implicitly[c.WeakTypeTag[T]]
        t.tpe.decls.foreach(println)

        //        assert(t.tpe.typeSymbol.asClass.isCaseClass, s"only support CaseClass, but ${t.tpe.typeSymbol.fullName} is not")

        reify {

            println("Hello2 ==> [" + person.splice + ", the time is: " + new Date().toString)
        }
    }

}

trait GenericRow[T] {
    def toRow(c: T): Map[String, Any]
}
