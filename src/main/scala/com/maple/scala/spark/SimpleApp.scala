package com.maple.scala.spark

import org.apache.spark.sql.{Dataset, Encoder, Encoders, SparkSession}
import org.apache.spark.sql.catalyst.encoders.ExpressionEncoder._

/**
  * 运行方式，打包此为 jar
  *
  * 运行
  * <pre>
  * $SPARK_HOME/bin/spark-submit \
  * --class "SimpleApp" \
  * --master local[4] \
  * target/scala-2.11/simple-project_2.11-1.0.jar
  * </pre>
  */
object SimpleApp {
  def main(args: Array[String]): Unit = {
    implicit val encoders: Encoder[String] = Encoders.STRING

    wrap {
      val logFile = "/Users/maple/mi/spark/README.md"
      val spark: SparkSession = SparkSession.builder.appName("Simple Application").getOrCreate()

      val logData = spark.read.textFile(logFile).cache()
      //      val numAs = logData.filter(line => line.contains("a")).count()
      //      val numBs = logData.filter(line => line.contains("b")).count()


      val numAs = logData.flatMap(l ⇒ l.split(" ")).filter(_.contains("a")).count()
      val numBs = logData.filter(line => line.contains("b")).reduce((a, b) ⇒ a + b)
      println(s"Lines with a: $numAs, Lines with b: $numBs")
      spark.stop()
    }
  }

  def wrap[T](f: ⇒ T): T = {
    val begin = System.currentTimeMillis()
    val result = f
    println(s"处理结束，耗时：${System.currentTimeMillis() - begin} ms")
    result
  }
}
