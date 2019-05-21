//package com.maple.scala.scalasql
//
//import java.sql.ResultSet
//
//import wangzx.scala_commons.sql.{CaseClassResultSetMapper, JdbcValueAccessor, JdbcValueAccessor_String, ResultSetEx}
//
//object ScalaSqlMacroApp {
//
//    def main(args: Array[String]): Unit = {
//
//    }
//
//    {
//        import wangzx.scala_commons.sql._
//        import java.sql.ResultSet
//        {
//            final class $anon extends CaseClassResultSetMapper[com.maple.scala.scalasql.MetricsCustomer] {
//
//                val metricDate = Field[String]("metricDate")
//                val orders = Field[Int]("orders")
//                val customerId = Field[Long]("customerId")
//                val appName = Field[String]("appName")
//                val companyName = Field[String]("companyName")
//                val createTime = Field[org.joda.time.DateTime]("createTime")
//
//                override def from(arg: ResultSet): com.maple.scala.scalasql.MetricsCustomer = {
//                    val rs = new ResultSetEx(arg)
//                    com.maple.scala.scalasql.MetricsCustomer(metricDate(rs), orders(rs), customerId(rs), appName(rs), companyName(rs), createTime(rs))
//                }
//            }
//            new $anon()
//        }
//    }
//
//}
//
//class MetricsCustomerBuild extends CaseClassResultSetMapper[com.maple.scala.scalasql.MetricsCustomer] {
//    val s = implicitly[JdbcValueAccessor[String]]
//
//    val metricDate = Field[String]("metricDate")(s)
//    val orders = Field[Int]("orders")
//    val customerId = Field[Long]("customerId")
//    val appName = Field[String]("appName")
//    val companyName = Field[String]("companyName")
//    val createTime = Field[org.joda.time.DateTime]("createTime")
//
//    override def from(arg: ResultSet): com.maple.scala.scalasql.MetricsCustomer = {
//        val rs = new ResultSetEx(arg)
//        com.maple.scala.scalasql.MetricsCustomer(metricDate(rs), orders(rs), customerId(rs), appName(rs), companyName(rs), createTime(rs))
//    }
//}
//
//
