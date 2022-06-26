//package com.maple.macros
//
//import com.maple.scala.macros.lw.GenericRow
//import com.maple.scala.macros.lw.GenericRow.toRow
//
//object GenericRowTest {
//
//    case class Goods(id: Int, name: String, price: Double, location: String = "China")
//
//    def main(args: Array[String]): Unit = {
//        GenericRow.greeting[String]("Maple")
//
//        val goods1 = Goods(1, "饼干", 23.4D)
//        val goods2 = Goods(1, "饼干", 23.4D, "USA")
//
//        val map1 = GenericRow.genericToRow(goods1)
//        val map2 = GenericRow.genericToRow(goods2)
//
//        println(map1)
//        println(map2)
//
//        println("x")
//
//    }
//
//}
