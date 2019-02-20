package com.maple.scala.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  private val master = "spark://spark1:7077"
  //  private val remote_file = "hdfs://spark1:9000/user/spark/data/spark.txt"
  private val remote_file = "/Users/maple/mi/spark/README.md"

  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster(master)
      .set("spark.executor.memory", "512m")
      .setJars(List("D:\\JetBrains\\workspace\\WordCount\\out\\artifacts\\WordCount_jar\\WordCount.jar"))

    val sc = new SparkContext(conf)
    val textFile = sc.textFile(remote_file)
    val wordCount = textFile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey((a, b) => a + b)
    wordCount.foreach(println)
  }


}
