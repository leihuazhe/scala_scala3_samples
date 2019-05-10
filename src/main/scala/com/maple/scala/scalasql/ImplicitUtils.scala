package com.maple.scala.scalasql

import java.sql.{PreparedStatement, ResultSet, Timestamp}

import org.joda.time.DateTime
import wangzx.scala_commons.sql.JdbcValueAccessor

import scala.language.implicitConversions

/**
  * Copyright (c) 2018 XiaoMi Inc. All Rights Reserved.
  * Authors: Maple <leihuazhe@xiaomi.com> on 19-4-28 19:06
  */
object ImplicitUtils {

    implicit object JdbcValueAccessor_Datetime extends JdbcValueAccessor[DateTime] {
        override def passIn(stmt: PreparedStatement, index: Int, value: DateTime): Unit = stmt.setTimestamp(index, new Timestamp(value.getMillis))

        override def passOut(rs: ResultSet, index: Int): DateTime = new DateTime(rs.getTimestamp(index))

        override def passOut(rs: ResultSet, name: String): DateTime = new DateTime(rs.getTimestamp(name))
    }

}
