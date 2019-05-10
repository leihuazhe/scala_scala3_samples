package com.maple.scala.implicits

class ImplicitlyTest2 {

}

trait ResultSetMapper {
    def from(rs: String): String
}

case class ResultSetMapper_String(content: String) extends ResultSetMapper {
    override def from(rs: String): String = s"$content -> $rs"
}

object ResultSetMapper {
    implicit def material: ResultSetMapper = ResultSetMapper_String("Dalao")
}

object Test extends  App {
    import ResultSetMapper._

    val resultSet = implicitly[ResultSetMapper]

    println(resultSet.from("OK"))

}

