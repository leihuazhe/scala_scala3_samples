package com.maple.scala.first

object StableRequiredTest {

  def main(args: Array[String]): Unit = {

    val idType = 3

    idType match {
      case x if x == IdType.mid.id ⇒ println("1")
      case x if x == IdType.imei.id ⇒ println("2")
      case _ ⇒ println("nothing")
    }

    val res: IdType.Value = IdType.apply(2)

    println(res)

  }

}

object IdType extends Enumeration {
  type IdType = Value

  val mid = Value(1)
  val imei = Value(2)
}
