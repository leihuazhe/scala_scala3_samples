package com.maple.scala3.derive2

/**
  * @author leihz
  * @version 1.0.0
  * @since 2022/08/14 00:53
  */
enum Opt[+T]derives Equal :
  case Some(t: T)
  case None

@main def test1(): Unit = {
  import Opt.*
  //Opt.derived$Equal[scala.Int](Equal.given_Equal_Int)
  val eqoi: Equal[Opt[Int]] = summon[Equal[Opt[Int]]]
  println("---- start call ----")
  //eqoi.equal((Opt.Sm.apply[scala.Int](23): Opt[scala.Int]), (Opt.Sm.apply[scala.Int](23): Opt[scala.Int]))
  val isEqual = eqoi.equal(Some(23), Some(23))
  println(s"isEqual: $isEqual")


}

@main def test2(): Unit = {
  val x1 = (1, 2, 3, 4)
  val x2 = 3 *: (4, 5, 6)
  //val x2 = scala.*: (4, 5, 6)
  println(x2)
  //val t1 = scala.*:
}

