package com.maple.scala3.turtoral.extensions

import java.sql.ResultSet
import java.sql.Connection

extension (conn: Connection)
  def query(sql: String): ResultSet =
    conn.prepareStatement(sql).executeQuery()

extension (str: String) def customUpper = s"Jeffrey: ${str.toUpperCase}";
