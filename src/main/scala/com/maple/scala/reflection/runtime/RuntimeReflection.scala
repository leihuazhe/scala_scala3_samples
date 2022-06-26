//package com.maple.scala.reflection.runtime
//
///**
//  * TypeTags
//  *
//  *
//  */
//object RuntimeReflection {
//
//    import scala.reflect.runtime.{universe ⇒ ru}
//
//    /**
//      * this is equivalent to defining an implicit “evidence” parameter, which causes the compiler to generate a TypeTag for T
//      */
//    def getTypeTag[T: ru.TypeTag](obj: T) = {
//        ru.typeTag
//    }
//
//    case class Person(name: String)
//
//    /**
//      * INSTANTIATING A TYPE AT RUNTIME
//      */
//    def instantiateByConstruct() = {
//        //First step, obtain a mirror by current classloader
//        val m: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
//        //The second step involves obtaining a ClassMirror for class Person using the reflectClass method.
//        val classPerson: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass
//        //The ClassMirror provides access to the constructor of class Person.
//        val classMirror: ru.ClassMirror = m.reflectClass(classPerson)
//
//        val ctor: ru.MethodSymbol = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod
//
//        val person: ru.MethodMirror = classMirror.reflectConstructor(ctor)
//
//        val res = person("Maple")
//
//        println(res)
//    }
//
//    case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)
//
//    /**
//      *
//      * 在有实例的情形下,对该实例的某个属性值进行修改
//      * 有实例的情况下 => InstanceMirror
//      *
//      *
//      * ACCESSING AND INVOKING MEMBERS OF RUNTIME TYPES
//      */
//    def accessMember() = {
//        val p = Purchase("Jeff Walter", 23819, shipped = false)
//
//        val m = ru.runtimeMirror(p.getClass.getClassLoader)
//
//        val im: ru.InstanceMirror = m.reflect(p)
//
//        val nameTermSymbol = ru.typeOf[Purchase].decl(ru.TermName("name")).asTerm
//
//        val shippingFieldMirror = im.reflectField(nameTermSymbol)
//
//        println(shippingFieldMirror.get)
//
//        println(p)
//        shippingFieldMirror.set("Maple Ray")
//
//        println(p)
//
//    }
//
//}
//
//object TypeTagsSpec {
//
//    import scala.reflect.runtime.{universe ⇒ ru}
//
//    def main(args: Array[String]): Unit = {
//        //        RuntimeReflection.instantiateByConstruct()
//        RuntimeReflection.accessMember()
//    }
//
//    def test2(): Unit = {
//        val l = List(1, 2, 3)
//
//        val lTypeTag = RuntimeReflection.getTypeTag(l)
//        val lType: ru.Type = lTypeTag.tpe
//
//        val res = lType.decls.take(100)
//
//        res foreach println
//
//    }
//
//}
//
//
