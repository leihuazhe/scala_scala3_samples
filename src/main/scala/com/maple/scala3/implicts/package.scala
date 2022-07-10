package com.maple.scala3.implicts

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
  * TODO
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/06/27 14:40
  */
package object startup

val formatterNormal: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

given Conversion[LocalDateTime, String] with
  override def apply(datetime: LocalDateTime): String = formatterNormal.format(datetime)