//package com.maple.quill
//
//import com.maple.`macro`.quill.QuillDatabase
//
//object HelloWorld {
//    val db = QuillDatabase
//    import db._
//
//    def main(args: Array[String]): Unit = {
//        val sql = quote {
//            query[Person].filter(_.name == "Maple Ray").map(_.name)
//        }
//
//        //val res: List[String] = db.run(sql)
//
//
//        val sql2 = quote {
//            query[Person]
//        }
//
//        db.run(insert(List(PersonDto("Maple Ray",27),PersonDto("YT May",26),PersonDto("Walter White",50))))
//
//
//        val res2: List[Person] = db.run(sql2)
//
////        res foreach println
//        res2 foreach println
//
//        Thread.sleep(2000)
//    }
//
//    def insert(persons: List[PersonDto]) = quote {
//        liftQuery(persons).foreach(c => querySchema[PersonDto]("person").insert(c))
//    }
//
////    case class Person(name: String, age: Int)
//
//}
