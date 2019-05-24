package com.maple.scala.reflection.runtime

case class Student(
                          name: String,
                          age: Int = 20,
                          gender: String = "BOY"
                  )

object GetCaseClassDefaultValue {

    import scala.reflect.runtime.universe._

    def main(args: Array[String]): Unit = {

        val result = Student.getClass.getMethod("$lessinit$greater$default$3").invoke(Student)

        println(result)

    }

}


