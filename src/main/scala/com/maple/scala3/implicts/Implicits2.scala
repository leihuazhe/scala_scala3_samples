package com.maple.scala3.implicts

import com.maple.scala3.implicts.Implicits2.formatterNormal
import com.maple.scala3.implicts.ImplicitsDemo.Person

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.language.{implicitConversions, postfixOps}

/**
  * Jeff H. Ray
  * June.26th.2022
  */
object Implicits2 {
  val formatterNormal: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
}

given Conversion[LocalDateTime, String] with
  override def apply(datetime: LocalDateTime): String = formatterNormal.format(datetime)



