package com.maple.scala.first

import scala.collection.{Map, mutable}

object CollectionTest {

    def main(args: Array[String]): Unit = {




        val b: mutable.Builder[(String, String), Map[String, String]] = Map.newBuilder[String,String]
        b += ("1"->"2")
        b += ("2"->"2")
        b += ("3"->"2")

        val map = b.result()


        println(s"$map = ${map.getClass}")

        val a = List.newBuilder[String]


        val c = Map("1"->"2")

    }

}
