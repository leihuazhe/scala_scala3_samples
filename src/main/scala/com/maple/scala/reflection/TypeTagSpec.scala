//package com.maple.scala.reflection
//
//import scala.reflect.runtime.universe
//
///*
//
//    但是当然它实际上无法为您提供这样使用时调用方法的泛型参数：
//
//
// */
//object TypeTagSpec {
//
//    import scala.reflect.runtime.universe._
//
//    /*
//    Error:(7, 25) No TypeTag available for T
//    def foo[T] = typeTag[T]
//     */
//    //    def foo[T] = typeTag[T]
//
//    def foo[T: TypeTag](obj: T): universe.TypeTag[T] = typeTag[T]
//
//    def weakFoo[T](obj: T): universe.Type = weakTypeOf[T]
//
//    def main(args: Array[String]): Unit = {
//        val res = foo(1)
//        println(res)
//    }
//
//}
