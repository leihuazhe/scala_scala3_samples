package com.maple.scala.second

import scala.concurrent.Future

object HighFuction2 {

  def query[T](name: String)(transfer: ⇒ T): Future[T] = {
//    Future.successful(transfer(s"Result=> $name"): T)
    ???
  }

  def main(args: Array[String]): Unit = {
//    val res = query("maple")(x:Int ⇒ x*2)
  }

  def transfers(name: Any) = {
    name.toString
  }

}
