//package com.maple.macros
//
//import com.maple.scala.macros.ExtractorMacrosSpec._
//
//object ExtractorMacrosTest {
//
//    def main(args: Array[String]): Unit = {
//
//        val fname = "William"
//        val lname = "Wang"
//        val ss = "Wang"
//
//        //new FreeUser("William","Wang")
//        val someuser = usr"$fname,$lname"
//
//        println(s"someuser:   $someuser")
//
//        someuser match {
//            case usr"$first,$last" => println(s"hello $first $last")
//        }
//    }
//
//}
