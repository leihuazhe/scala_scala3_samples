package com.maple.scala.first

/**
  * view 和 slice 的区别
  *
  * slice create a new List
  * view is just a view
  */
object ForComprehendSpec {

    def test1(): Unit = {
        val list = List("a", "b", "c", "d", "e", "f", "g", "h")

        for (i <- list.indices) yield println("A-" + list(i) + s"-$i")

    }

    def test2(): Unit = {
        val list = List("a", "b", "c", "d", "e", "f", "g", "h")

        list.map(x ⇒ "")

        for ((result, index) <- list.view.zipWithIndex) {

            println("A-" + result + s"-$index")
        }

    }

    def main(args: Array[String]): Unit = {
        test2()
    }

}
