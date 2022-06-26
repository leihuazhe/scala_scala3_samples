package com.maple.scala3.implicts

import java.sql.Connection


class RichConnection(conn: Connection) {

  //定义的新方法 executeUpdate，对数据操作
  def executeUpdate(sql: String): Int = {
    conn.prepareStatement(sql).executeUpdate()
  }
}

given given_to_rich: Conversion[Connection, RichConnection] with
  override def apply(conn: Connection): RichConnection = new RichConnection(conn)