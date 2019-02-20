package com.maple.scala.types


class Pet(val name: String) {
  override def toString: String = name
}

class Dog(override val name: String) extends Pet(name)


object CovarianceDemo {

  //需要协变，接收父类时,传入的是子类。
  def workWithPets(pets: Array[Pet]): Unit = {
    pets.foreach(println)
  }

  def playWithPets[T <: Pet](pets: Array[T]): Unit = {
    pets.foreach(println)
  }

  def main(args: Array[String]): Unit = {
    val dogs = Array(new Dog("A"), new Dog("B"))
//    workWithPets(dogs)
    playWithPets(dogs)
  }
}
