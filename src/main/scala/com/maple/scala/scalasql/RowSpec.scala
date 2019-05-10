package com.maple.scala.scalasql

import org.joda.time.DateTime
import wangzx.scala_commons.sql._
import DBUtils.scalaSqlDB
import ImplicitUtils._

object RowSpec {

    def main(args: Array[String]): Unit = {
        getAllCustomerAdOwner()
    }

    def getAllCustomerAdOwner(): Unit = {
        val metricDate = "20190509"
        val yesterdayDatas = scalaSqlDB.rows[MetricsCustomer](sql"SELECT * from ad_time_owner where metric_date = ${metricDate} ")
        yesterdayDatas.foreach(println)
    }

}

case class MetricsCustomer(
                                  metricDate: String,
                                  orders: Int,
                                  customerId: Long,
                                  appName: String,
                                  companyName: String,
                                  createTime: DateTime
                          )