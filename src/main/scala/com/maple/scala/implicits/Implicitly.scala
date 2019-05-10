package com.maple.scala.implicits

object Implicitly {

    def main(args: Array[String]): Unit = {
        test2()
    }

    /**
      * Context Bounds.
      */
    def main1(): Unit = {
        //        1.min(2)
        //        val res1: Int ⇒ {def min(i: Int): Any} = implicitly[Int => {def min(i: Int): Any}]

        val p = Person("bob", 25)

        val res = implicitly[Show[Person]].show(p)

        val res1 = Person.PersonShow(si = implicitly, ss = Show.shoutyStringShow).show(p)

        println(res)
        println(res1)
    }


    def test2(): Unit ={
        implicit val a = "test"
        val b = implicitly[String]

        println(b)
    }

}

trait Show[T] {
    def show(t: T): String
}

object Show {

    implicit def IntShow: Show[Int] = new Show[Int] {
        def show(i: Int) = i.toString
    }

    implicit def StringShow: Show[String] = new Show[String] {
        def show(s: String) = s
    }

    def shoutyStringShow: Show[String] = new Show[String] {
        def show(s: String) = s.toUpperCase
    }
}

case class Person(name: String, age: Int)

object Person {
    implicit def PersonShow(implicit si: Show[Int], ss: Show[String]): Show[Person] = new Show[Person] {
        def show(p: Person) = "Person(name=" + ss.show(p.name) + ", age=" + si.show(p.age) + ")"
    }
}
