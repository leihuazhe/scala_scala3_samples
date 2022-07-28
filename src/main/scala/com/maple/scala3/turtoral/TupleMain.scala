package com.maple.scala3.turtoral

import scala.runtime.Tuples

object TupleMain:

  /*
  def summonAll[T: Type](using Quotes): List[Expr[Eq2[_]]] =
    Type.of[T] match
      case '[String *: tpes] => '{ summon[Eq2[String]] } :: summonAll[tpes]
      case '[Int *: tpes]    => '{ summon[Eq2[Int]] } :: summonAll[tpes]
      case '[tpe *: tpes]    => derived[tpe] :: summonAll[tpes]
      case '[EmptyTuple]     => Nil
  */

  @main def test1() = {
    val x1 = (1, 2)
    val x2 = (2, 3)
    val x3 = Tuple1(4)
    doMatch(x1)
    doMatch(x2)
    doMatch(x3)
    doMatch(Tuples.fromArray(Array.empty))
  }

  def doMatch(t: Tuple) = {
    t match {
      case 1 *: te ⇒ println(te)
      case 2 *: te ⇒ println(te)
      case EmptyTuple ⇒ println("Nil")
      case _ ⇒ println("_")
    }
  }
