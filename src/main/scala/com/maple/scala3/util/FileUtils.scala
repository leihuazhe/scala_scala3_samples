package com.maple.scala3.util


import java.io.{IOException, InputStream}
import scala.io.Source

/**
  * read file
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/10 13:41
  */
object FileUtils:

  def read(path: String): String = Source.fromResource(path).getLines().mkString
