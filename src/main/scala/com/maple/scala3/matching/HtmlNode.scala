//package com.maple.scala3.matching
//
//
//
//// The match expression patterns do not have guards
//// The match expression scrutinee's type is a subtype of the match type scrutinee's type
//// The match expression and the match type have the same number of cases
//// The match expression patterns are all Typed Patterns, and these types are =:= to their corresponding type patterns in the match type
//
//type HtmlNodeRecord[X] = x match {
//  case "tag" ⇒ String
//  case "attrs" ⇒ List[(String, String)]
//  case "children" ⇒ List[HtmlNode]
//}
//
//case class HtmlNode(tag: String, attrs: List[(String, String)], children: List[HtmlNode]) {
//  def apply(s: "tag" | "attrs" | "children"): HtmlNodeRecord[s.type] = s match {
//    case _: "tag" => tag
//    case _: "attrs" => attrs
//    case _: "children" => children
//  }
//}
//
//object HtmlNode {
//
//
//}
//
////case class HtmlNode(tag: String, attrs: List[(String, String)], children: List[HtmlNode]) {
////  def apply(s: "tag" | "attrs" | "children"): HtmlNodeRecord[s.type] = s match {
////    case "tag" => tag
////    case "attrs" => attrs
////    case "children" => children
////  }
////}
//
