package com.maple.scala.reflection.runtime


/**
  * TypeTags
  *
  *
  */
object RuntimeReflection2 {

    import scala.reflect.runtime.{universe ⇒ ru}

    /**
      * this is equivalent to defining an implicit “evidence” parameter, which causes the compiler to generate a TypeTag for T
      */
    def getTypeTag[T: ru.TypeTag](obj: T) = {
        ru.typeTag
    }

    case class Person(name: String, age: Int, gender: String = "MAPLE")

    /**
      * INSTANTIATING A TYPE AT RUNTIME
      */
    def instantiateByConstruct() = {
        val m = ru.runtimeMirror(getClass.getClassLoader)

        val classPerson: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass

        val classMirror = m.reflectClass(classPerson)

        val constructor: ru.MethodSymbol = classPerson.primaryConstructor.asMethod
        //        val ctors = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod

        val companion: ru.Symbol = classPerson.companion

        inspectTheClass(classPerson, constructor, companion)

        val im = classMirror.reflectConstructor(constructor)

        println(im("Maple", 22, "BOY"))

    }

    def inspectTheClass(classPerson: ru.ClassSymbol, constructor: ru.MethodSymbol, companion: ru.Symbol): Unit = {
        import scala.reflect.runtime.universe._
        var index = 0
        val args: List[(Tree, Tree)] = constructor.paramLists.head.map { p ⇒

            val term: ru.TermSymbol = p.asTerm
            index += 1

            val name = term.name.toString
            val newTerm = ru.TermName(name.toString)

            val tree = if (term.isParamWithDefault) {
                val defMethod: ru.Symbol = classPerson.companion.asModule.typeSignature.member(ru.TermName("$lessinit$greater$default$" + index))
                q"""val $newTerm = Field[${term.typeSignature}]($name, Some($companion.$defMethod) )"""
            }
            else
                q"""val $newTerm = Field[${term.typeSignature}]($name) """

            (q"""${newTerm}(rs)""", tree)
        }

        args.foreach {
            case (x, y) ⇒
                println(s"$x ---------------- $y")
        }
    }

    case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)

    /**
      *
      * 在有实例的情形下,对该实例的某个属性值进行修改
      * 有实例的情况下 => InstanceMirror
      *
      *
      * ACCESSING AND INVOKING MEMBERS OF RUNTIME TYPES
      */
    def accessMember() = {
        val p = Purchase("Walter White", 20, shipped = true)
        val m = ru.runtimeMirror(p.getClass.getClassLoader)
        val im = m.reflect(p)

        val pTypes: ru.Type = ru.typeOf[Purchase]

        val nameFiledSymbol = ru.typeOf[Purchase].member(ru.TermName("name")).asTerm
        //        val nameFiledSymbol = ru.typeOf[Purchase].decl(ru.TermName("name")).asTerm

        val mirror: ru.FieldMirror = im.reflectField(nameFiledSymbol)

        println(mirror.get)
        mirror.set("Maple Ray")
        println(p)

    }

}

object TypeTagsSpec2 {

    def main(args: Array[String]): Unit = {



        //        RuntimeReflection.instantiateByConstruct()
        RuntimeReflection2.instantiateByConstruct()
    }

}


