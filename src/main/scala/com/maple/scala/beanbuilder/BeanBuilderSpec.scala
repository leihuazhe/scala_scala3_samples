//package com.maple.scala.beanbuilder
//
//import wangzx.scala_commons.sql.BeanBuilder
//
//
//object BeanBuilderSpec {
//
//    def main(args: Array[String]): Unit = {
//        val p = Person("maple", 20)
//        val gender = Gender("男", "BOY")
//
////        val student = BeanBuilder.build[Student](p)("gender" → gender)
////
////        println(student)
//    }
//
//}
//
//case class Person(name: String, age: Int)
//
//case class Gender(name: String, nick: String)
//
//case class Student(name: String, age: Int, gender: Gender)
