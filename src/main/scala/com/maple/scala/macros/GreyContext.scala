package com.maple.scala.macros

import scala.language.experimental.macros

/**
  *
  */
object GreyContext {

    class Impl(val c: scala.reflect.macros.whitebox.Context) {

        import c.universe._

        def mono: c.Expr[Unit] = c.literalUnit

        def poly[T: c.WeakTypeTag]: c.universe.Tree = q"${c.weakTypeOf[T].toString}"
    }

    object Macros {
        //        def mono = macro Impl.mono

        //        def poly[T] = macro Impl.poly[T]
    }

}
