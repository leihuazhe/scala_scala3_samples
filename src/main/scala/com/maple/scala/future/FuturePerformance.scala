//package com.maple.scala.future
//
//import scala.concurrent.Future
//import scala.io.Source
//import scala.concurrent.ExecutionContext.Implicits.global
//import scala.util.{Failure, Success}
//
//object FuturePerformance {
//
//  def callFuture(): Unit = {
//    val futureRes = Future {
//      readFromDisk()
//    }
//    val future2: Future[List[String]] = futureRes.flatMap((s: Future[List[String]]) => sendToRemote(s))
//
//    future2 onComplete {
//      case Success(value) => println(value)
//      case Failure(exception) => println(exception)
//    }
//
//    future2 recoverWith {
//      case _: Exception => Future.successful("error")
//    }
//
//    future2 recover {
//      case _: Exception => "maple"
//    }
//
//
//    //type 2
//    val future3 = for (
//      data <- readFromDisk();
//      response <- sendToRemote2(data)
//    ) yield response
//
//    println(future3)
//
//  }
//
//  def readFromDisk() = {
//    Future {
//      Source.fromInputStream(getClass.getClassLoader.getResourceAsStream("future.txt"))
//        .getLines().flatMap(line => line.split(" ")).toList
//    }
//  }
//
//  def sendToRemote(input: Future[List[String]]) = {
//    input.foreach(i => s"record the input,this round is $i")
//    input
//  }
//  def sendToRemote2(input: List[String]) = {
//    input.foreach(i => s"record the input,this round is $i")
//    input
//  }
//
//}
