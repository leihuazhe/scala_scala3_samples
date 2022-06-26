package com.maple.scala.future

import scala.collection.mutable.ArrayBuffer
//import scala.collection.parallel.immutable.{ParMap, ParSeq}
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}


case class Task(name: String, cost: Long)

object ParallelMapTest {

  def main(args: Array[String]): Unit = {
    //test2()
  }


  def test1(): Unit = {
    val costMap = Map("1" -> Task("1", 1), "2" -> Task("2", 2), "3" -> Task("3", 3))

    val begin = System.currentTimeMillis()

    val listOfFutures: Seq[Future[String]] = costMap.map {
      case (x, task) => {
        Future {
          Thread.sleep(task.cost * 1000)
          task.name
        }
      }
    }.toList

    val futureOfList = Future.sequence(listOfFutures)


    futureOfList onComplete {
      case Success(x) => {
        val end = System.currentTimeMillis()
        println(s"结果 $x, 耗时： ${end - begin}")
      }
      case Failure(ex) => println("Failed !!! " + ex)
    }

    Thread.sleep(Long.MaxValue)
  }

//  def test2(): Unit = {
//    val results: List[Task] = List(Task("1", 1), Task("2", 2), Task("3", 3))
//
//    val res: ParMap[String, Task] = results.par.flatMap {
//      result => Map(result.name -> result)
//    }.toMap
//
//    val res1: Map[String, Task] = results.par.flatMap {
//      result => Map(result.name -> result)
//    }.toList.toMap
//
//    res1 foreach {
//      case (x,y) => println(s"$x- $y")
//    }
//  }


}
