//package com.maple.scala.xiaomi
//
///*
//class Foo extends Dynamic {
//  // Expressions are only rewritten to use Dynamic if they are not already valid
//  // Therefore foo.realField will not use select/updateDynamic
//  var realField: Int = 5
//
//  // Called for expressions of the type foo.field
//  def selectDynamic(fieldName: String) = ???
//
//  def updateDynamic(fieldName: String)(value: Int) = ???
//}*/
//
//object Roman extends Dynamic {
//
//
//  def selectDynamic(name: String): Int = {
//    val s = name.replaceAll("IV", "IIII")
//      .replaceAll("IX", "VIIII")
//      .replaceAll("XL", "XXXX")
//      .replaceAll("XC", "LXXXX")
//    s.count(_ == 'I') +
//      s.count(_ == 'V') * 5 +
//      s.count(_ == 'X') * 10 +
//      s.count(_ == 'L') * 50 +
//      s.count(_ == 'C') * 100
//  }
//
//
//}
//
//object Tips2DynamicInvocation {
//
//  def test1(): Unit = {
//    println(Roman.VI) //  6
//    println(Roman.X) // 10
//    println(Roman.XII) // 12
//    println(Roman.XV) // 15
//  }
//
//
//  def main(args: Array[String]): Unit = {
//    test1
//
//  }
//}
