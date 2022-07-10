package com.maple.scala3.wjson.serialization

import wjson.*

/**
  * TODO
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/10 16:55
  */
object Serial1:

  @main def serializable(): Unit = {
    case class Person(name: String, age: Int)
    val jsval = json"""{"name":"John","age":18}"""

    val person = jsval.convertTo[Person] // jsval 具有扩展方法 to[T] 可以反序列化为 T（必须是case class）
    val jsval2 = person.toJson // case class 具有扩展方法 toJson 可以序列化为 JsVal
    println(person)
    println(jsval2)
  }
