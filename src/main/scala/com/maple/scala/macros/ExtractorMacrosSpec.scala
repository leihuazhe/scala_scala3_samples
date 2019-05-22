package com.maple.scala.macros

import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context

object ExtractorMacrosSpec {

    trait User {
        val fname: String
        val lname: String
    }

    case class FreeUser(fname: String, lname: String) extends User {
        val i = 10

        def f: Int = 1 + 2

    }

    //extends User
    class PremiumUser(val name: String, val gender: Char, val vipnum: String)

    implicit class UserInterpolate(sc: StringContext) {

        object usr {
            def apply(args: String*): Any = macro ExtractorMacrosSpec.appl

            def unapply(u: User): Any = macro ExtractorMacrosSpec.uapl
        }

    }

    def appl(c: Context)(args: c.Tree*): c.Tree = {
        import c.universe._
        val argList = args.toList
        q"new FreeUser(..$argList)"
    }

    /**
      * 前面大部分代码就是为了形成List[Tree] qget和qdef,最后组合一个完整的quasiquote q""" new {...}"""。
      */
    def uapl(c: Context)(u: c.Tree): c.Tree = {
        import c.universe._

        val params = u.tpe.members.collectFirst {
            case m: MethodSymbol if m.isPrimaryConstructor => m.asMethod
        }.get.paramLists.head.map { p => p.asTerm.name.toString }

        val (qget, qdef) = params.length match {
            case len if len == 0 =>
                (List(q""), List(q""))
            case len if len == 1 =>
                val pn = TermName(params.head)
                (List(q"def get = u.$pn"), List(q""))
            case _ =>
                val defs = List(q"def _1 = x", q"def _2 = x", q"def _3 = x", q"def _4 = x")
                val qdefs = (params zip defs).collect {
                    case (p, d) =>
                        val q"def $mname = $mbody" = d
                        val pn = TermName(p)
                        q"def $mname = u.$pn"
                }
                (List(q"def get = this"), qdefs)
        }

        q"""
             new {
               class Matcher(u: User) {
                 def isEmpty = false
                 ..$qget
                 ..$qdef
               }
               def unapply(u: User) = new Matcher(u)
             }.unapply($u)
           """
    }
}
