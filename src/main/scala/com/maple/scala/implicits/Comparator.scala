//package com.maple.scala.implicits
//
//import java.util
//import scala.language.implicitConversions.*
//
///**
//  * TODO
//  *
//  * @author leihz
//  * @version 1.0.0
//  * @since 2022/06/24 18:25
//  */
//object Comparator {
//
//  trait Comparator[T] {
//
//    def compare(a: T, b: T): Int
//
//  }
//
//  object ComparatorInstances {
//    //type of implicit definition needs to be given explicitly
//    /*implicit val intComparator = new Comparator[Int] {
//      override def compare(a: Int, b: Int): Int = {
//        a.compareTo(b);
//      }
//    }
//
//    implicit def listComparator[T](implicit cmp: Comparator[T]) = new Comparator[List[T]] {
//      override def compare(a: List[T], b: List[T]): Int = {
//        (a, b) match {
//          // 连个都为空
//          case (Nil, Nil) => 0
//          // 某一个为空
//          case (Nil, _) => 1
//          case (_, Nil) => -1
//          // 两个都不为空，
//          case (l, r) =>
//            // 比较头结点
//            cmp.compare(l.head, r.head) match {
//              // List.tail 表示除了头结点以外的结点
//              case 0 => compare(l.tail, r.tail)
//              case num => num
//            }
//        }
//      }
//    }*/
//
//
//    given intComparator with Comparator[Int]
//    {
//      override def compare(a: Int, b: Int) = a.compareTo(b)
//    }
//  }
//
//  object Same {
//    def apply[T](a: T, b: T)(implicit cmp: Comparator[T]) = cmp.compare(a, b) == 0
//  }
//
//  object Main {
//    def main(args: Array[String]): Unit = {
//      import ComparatorInstances._
//      println(Same(1, 2))
//      println(Same(List(1, 2), List(1, 2)))
//
//    }
//  }
//}
