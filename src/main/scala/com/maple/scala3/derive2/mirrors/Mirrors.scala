package com.maple.scala3.derive2.mirrors

import com.maple.scala3.derive2.`macro`.Log

import scala.deriving.Mirror


/**
  * Mirrors
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/08/14 13:05
  */
case class A(i: Int, s: String, b: Boolean)


@main def mirrorOfA(): Unit = {
  val m = summon[Mirror.Of[A]]
  //scala.*:[scala.Int, scala.*:[scala.Predef.String, scala.*:[scala.Boolean, scala.Tuple$package.EmptyTuple]]]
  println(Log.describe[m.MirroredElemTypes])
  
}