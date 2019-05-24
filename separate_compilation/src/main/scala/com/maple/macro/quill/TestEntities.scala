package com.maple.`macro`.quill

import io.getquill.context.Context

trait TestEntities {
    this: Context[_, _] =>

    case class Person(id: Int, name1: String, age: Int)

    case class PersonDto(name: String, age: Int)

    val qr1 = quote {
        query[Person]
    }
}
