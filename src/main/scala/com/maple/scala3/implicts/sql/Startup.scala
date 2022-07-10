package com.maple.scala3.implicts.sql

import com.maple.scala3.implicts.RichConnection

import java.sql.Connection
import com.maple.scala3.implicts.sql.given_Conversion_Connection_RichConnection

/**
  * @author leihz
  * @version 1.0.0
  * @since 2022/06/27 14:54
  */
given Conversion[Connection, RichConnection] with
  override def apply(x: Connection): RichConnection = RichConnection(x)

class RichConnection(conn: Connection) {
  //定义的新方法 executeUpdate，对数据操作
  def executeUpdate(sql: String): Int = {
    conn.prepareStatement(sql).executeUpdate()
  }
}

@main def testConnection(): Unit = {
  var con: Connection = null
  richConnection(con)
}

def richConnection(conn: Connection): Unit =

  conn.executeUpdate("update name from users set name = ")


