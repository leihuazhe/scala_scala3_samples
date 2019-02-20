//package com.maple.scala.types
//
//
//class Pet(val name: String) {
//  override def toString: String = name
//}
//
//class Dog(override val name: String) extends Pet(name)
//
//
////contravariance
//object ContravarianceDemo {
//
//  def copyPets[S, D >: S](fromPets: Array[S], toPets: Array[D]): Unit = { //...
//
//  }
//
//  def main(args: Array[String]): Unit = {
//    val dogs = Array(new Dog("A"), new Dog("B"))
//
//    val pets = new Array[Pet](10)
//
//    copyPets(dogs, pets)
//  }
//
//}
