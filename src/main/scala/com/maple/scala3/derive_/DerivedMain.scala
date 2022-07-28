package com.maple.scala3.derive_

object Derives:
  enum Opt[+T] derives Eq:
    case Sm(t: T)
    case Nn
  enum Opt2[+T]:
    case Sm(t: T)
    case Nn

  @main def testDerived1() = {
    import Opt.*
    val eqoi = summon[Eq[Opt[Int]]]
    assert(eqoi.eqv(Sm(23), Sm(23)))

    val eqoi2 = summon[Eq[Opt[Double]]]
    assert(eqoi2.eqv(Sm(23.4), Sm(23.4)))

    val eqoi3 = summon[Eq[Opt[All]]]
    assert(!eqoi3.eqv(Sm(All("maple")), Sm(All("jeffrey"))))
  }

  case class Person(name: String, age: Int)

  @main def test2() = {
    val p1 = Opt2.Sm(Person("maple", 23))
    val p2 = Opt2.Sm(Person("jeffrey", 23))
    val p3 = Opt2.Sm(Person("maple", 23))

    val isEqual = p1 === p2
    println(s"p1===p2? $isEqual")
    println(s"p2===p3? ${p2 === p3}")
    println(s"p1===p3? ${p1 === p3}")
  }
