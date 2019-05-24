package com.maple.scala.operations

object AtOperation {

    def main(args: Array[String]): Unit = {
        test1
    }

    /*
    The effect of the @ operator is to alias the value matched on the left to the name on the right for the match.


    @ can be used to bind a name to a successfully matched pattern, or sub-pattern.

    Patterns can be used in pattern matching, the left hand side of the <- in for comprehensions, and in destructuring assignments.

     */
    def test1(): Unit = {
        val p = Option(2)

        p match {
            case x@Some(_) ⇒ println(x) //Some(2)
            case None ⇒ println("None")
        }

        val d@(c@Some(a), Some(b)) = (Some(1), Some(2))

        println(s"a: $a, b: $b, c: $c, d: $d")
        //a: 1, b: 2, c: Some(1), d: (Some(1),Some(2))

        (Some(1), Some(2)) match {
            case d@(c@Some(a), Some(b)) => println(a, b, c, d)
        }

        for (x@Some(y) <- Seq(None, Some(1))) println(x, y)

        val List(x, xs@_*) = List(1, 2, 3)
        println(x, xs)

    }

}
