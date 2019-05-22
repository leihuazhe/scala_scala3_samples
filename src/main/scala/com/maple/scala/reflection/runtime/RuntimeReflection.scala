package com.maple.scala.reflection.runtime

/**
  * TypeTags
  *
  *
  *
  *
  *
  *
  *
  *
  */
object RuntimeReflection {

    object TypeTagsSpec {

        import scala.reflect.runtime.{universe ⇒ ru}

        /**
          * this is equivalent to defining an implicit “evidence” parameter, which causes the compiler to generate a TypeTag for T
          */
//        def getTypeTag[T: ru.TypeTag[T]](obj: T) = ru.typeTag[T]

        def getTypeTag[T: ru.TypeTag](obj: T) = {
            ru.typeTag
        }

        def main(args: Array[String]): Unit = {
            val l = List(1, 2, 3)

            val res = getTypeTag(l)

            println(res)
            println(res.tpe)
        }

    }

}


