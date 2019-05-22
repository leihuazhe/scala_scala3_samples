package com.maple.macros

import com.maple.scala.macros.CaseClassMapConverter

object ConvertDemo {

    def ccToMap[C: CaseClassMapConverter](c: C): Map[String, Any] = implicitly[CaseClassMapConverter[C]].toMap(c)

    def mapTocc[C: CaseClassMapConverter](m: Map[String, Any]): C = implicitly[CaseClassMapConverter[C]].fromMap(m)

    case class Person(name: String, age: Int)

    case class Car(make: String, year: Int, manu: String)

    def main(args: Array[String]): Unit = {
        val civic = Car("Civic", 2016, "Honda")
        val person = Person("john", 18)

        val mapJohn = ccToMap[Person](person)
        val mapCivic = ccToMap[Car](civic)

        println(s"map person: $mapJohn")
        println(s"map car: $mapCivic")

        println("back to cc person: " + mapTocc[Person](mapJohn))
        println("back to cc car: " + mapTocc[Car](mapCivic))
    }

}