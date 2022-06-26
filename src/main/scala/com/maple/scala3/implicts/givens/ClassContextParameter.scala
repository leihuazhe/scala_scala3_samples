package com.maple.scala3.implicts.givens


class GivenIntBox(using val givenInt: Int):
  def n = summon[Int]


class GivenIntBox2(using givenInt: Int):
  given Int = givenInt

//def n = summon[Int] // ambiguous
