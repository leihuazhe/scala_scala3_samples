package com.maple.scala3.implicts



import scala.language.{implicitConversions, postfixOps}

class ImplicitsParameter {


//  def maxListUpBound2[T](elements: List[T])(using orders: T ⇒ Ordering[T]): T = {
//    elements match {
//      case List() =>
//        throw new IllegalArgumentException("empty list!")
//      case List(x) => x
//      case x :: rest =>
//        val maxRest = maxListUpBound2(rest)
//        if (x > maxRest) x
//        else maxRest
//    }
//  }
}
