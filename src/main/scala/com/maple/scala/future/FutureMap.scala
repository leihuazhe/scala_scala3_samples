package com.maple.scala.future

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.Try
import scala.concurrent.duration._

class FutureMap {

}

object FutureMap {


  def getFuture() = {
    val nums = List(1, 2, 3, 4,5)

    val res = {
//      if (nums.size != 5) throw new RuntimeException("on purpose throw exception")
      if (nums.size != 5) throw new RuntimeException("on purpose throw exception")
      else Future.successful("You are beautiful.")
    }

    val res1: String = Await.result(res,1000 second )
    println(res1)

  }


  def main(args: Array[String]): Unit = {
    getFuture
   /* getFuture().map { response =>
      println(
        response
      )

    }

    Thread.sleep(5000)*/


  }

}
