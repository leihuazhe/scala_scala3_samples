package com.maple.scala3.turtoral.extensions

import java.sql.Connection
import java.nio.file.SimpleFileVisitor
import java.nio.file.Path
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.FileVisitResult
import io.getquill.parser.Unlifter.caseClass
import com.maple.scala3.turtoral.extensions._

/** ExtensionMain
  *
  * @author
  *   leihz
  * @version 1.0.0
  * @since 2022/07/17
  *   16:19
  */
object ExtensionMain {

    @main
    def print() = {
      println("Hello world.")
    }

    @main
    def test1(): Unit = {
      val  conn = fetchConnection
      conn.query("select * from user")
    }

    @main
    def test2() = {
      val str    = "Maple"
      val newStr = str.customUpper
      println("newStr: $newStr")

    }

    def fetchConnection: Connection = ???

    def match1() = {
      new SimpleFileVisitor[Option[Path]] {
        override def visitFile(file: Option[Path], attrs: BasicFileAttributes): FileVisitResult = {
          file match {
            case Some(t) => t
            case None    => println("none")
          }
          null
        }
      }

      val str = "1"
    }

    enum Color:
      case RED, BLACK, GREEN, YELLOW;

    def color(t: Color): Unit = {}
}