package com.maple.scala.contextbound

object ContextBoundSpec {

    def foo1[T](a: T, b: T)(implicit stringer: Stringer[T]): String = {
        stringer.toString(a, b)
    }

    def foo2[T: Stringer](a: T, b: T): String = {
        val stringer = implicitly[Stringer[T]]
        stringer.toString(a, b)
    }

    def main(args: Array[String]): Unit = {
        implicit val stringer: Stringer[Int] = new Stringer[Int]() {
            override def toString(a: Int, b: Int): String = {
                s"$a-$b"
            }
        }

        val result1 = foo1(2, 3)
        val result2 = foo2(2, 3)

        println(result1)
        println(result2)

    }

}

trait Stringer[T] {
    def toString(a: T, b: T): String
}

