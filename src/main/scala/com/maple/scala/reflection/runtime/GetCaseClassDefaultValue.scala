package com.maple.scala.reflection.runtime

import scala.reflect.runtime.universe

case class Student(
                          name: String,
                          age: Int = 20,
                          gender: String = "BOY"
                  )

object GetCaseClassDefaultValue {

    import scala.reflect.runtime.universe._

    def main(args: Array[String]): Unit = {

        val result = Student.getClass.getMethod("$lessinit$greater$default$2").invoke(Student)

        println(result)

        val m = runtimeMirror(getClass.getClassLoader)

        val classStudent = m.typeOf[Student].typeSymbol.asClass

        val cm = m.reflectClass(classStudent)

        val constructor = classStudent.primaryConstructor.asMethod

        val instanteMirror = cm.reflectConstructor(constructor)

        val members: universe.MemberScope = m.typeOf[Student].members


    }

}


