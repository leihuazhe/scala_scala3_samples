package com.maple.scala3.implicts

case class Multiply(m: Int, n: Int):

  def multiply(other: Multiply): Multiply =
    Multiply(other.m * m, other.n * n)


given Conversion[Int, Multiply] with
  override def apply(x: Int): Multiply = Multiply(x, 2)

//given Conversion[Int, Multiply] with
//  override def apply(x: Int): Multiply = Multiply(x, 3)