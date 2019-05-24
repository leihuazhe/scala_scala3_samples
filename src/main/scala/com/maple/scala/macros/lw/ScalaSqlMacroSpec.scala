package com.maple.scala.macros.lw

import scala.reflect.runtime.{universe ⇒ ru}

object ScalaSqlMacroSpec {

    def main(args: Array[String]): Unit = {
        instantiateByConstruct()
    }


    case class Person(name: String, age: Int, gender: String = "MAPLE")

    /**
      * INSTANTIATING A TYPE AT RUNTIME
      */
    def instantiateByConstruct() = {
        val m = ru.runtimeMirror(getClass.getClassLoader)

        val classPerson: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass

        val classMirror = m.reflectClass(classPerson)

        val constructor: ru.MethodSymbol = classPerson.primaryConstructor.asMethod
        //        val ctors = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod

        val companion: ru.Symbol = classPerson.companion

        inspectTheClass(classPerson, constructor, companion)

        val im = classMirror.reflectConstructor(constructor)

        println(im("Maple", 22, "BOY"))

    }

    def inspectTheClass(classPerson: ru.ClassSymbol, constructor: ru.MethodSymbol, companion: ru.Symbol): Unit = {
        import scala.reflect.runtime.universe._
        var index = 0
        val args: List[(Tree, Tree)] = constructor.paramLists.head.map { p ⇒

            val term: ru.TermSymbol = p.asTerm
            index += 1

            val name = term.name.toString
            val newTerm = ru.TermName(name.toString)

            val tree = if (term.isParamWithDefault) {
                val defMethod: ru.Symbol = classPerson.companion.asModule.typeSignature.member(ru.TermName("$lessinit$greater$default$" + index))
                q"""val $newTerm = Field[${term.typeSignature}]($name, Some($companion.$defMethod) )"""
            }
            else
                q"""val $newTerm = Field[${term.typeSignature}]($name) """

            (q"""${newTerm}(rs)""", tree)
        }

        args.foreach {
            case (x, y) ⇒
                println(s"$x ---------------- $y")
        }
    }

    def generateCaseClassResultSetMapper[T: c.WeakTypeTag](c: scala.reflect.macros.whitebox.Context): c.Tree = {
        import c.universe._

        val t: c.WeakTypeTag[T] = implicitly[c.WeakTypeTag[T]]
        assert(t.tpe.typeSymbol.asClass.isCaseClass, s"only support CaseClass, but ${t.tpe.typeSymbol.fullName} is not")

        val companion = t.tpe.typeSymbol.asClass.companion

        val constructor: c.universe.MethodSymbol = t.tpe.typeSymbol.asClass.primaryConstructor.asMethod

        var index = 0
        // (q"address(rs)", q"""val address = Field[String]("address", defaultValue)""")
        val args: List[(c.Tree, c.Tree)] = constructor.paramLists(0).map { (p: c.universe.Symbol) =>
            val term: c.universe.TermSymbol = p.asTerm
            index += 1

            // search "apply$default$X"
            val name = term.name.toString
            val newTerm = TermName(name.toString)

            val tree = if (term.isParamWithDefault) {
                val defMethod: c.universe.Symbol = t.tpe.typeSymbol.asClass.companion.asModule.typeSignature.member(TermName("$lessinit$greater$default$" + index))
                q"""val $newTerm = Field[${term.typeSignature}]($name, Some($companion.$defMethod) )"""
            }
            else
                q"""val $newTerm = Field[${term.typeSignature}]($name) """

            (q"""${newTerm}(rs)""", tree)
        }

        val tree =
            q"""
         import wangzx.scala_commons.sql._
         import java.sql.ResultSet

         new CaseClassResultSetMapper[$t] {
          ..${args.map(_._2)}

          override def from(arg: ResultSet): $t = {
            val rs = new ResultSetEx(arg)
            new $t( ..${args.map(_._1)} )
          }
         }
       """

        //    println(tree);
        tree
    }

}
