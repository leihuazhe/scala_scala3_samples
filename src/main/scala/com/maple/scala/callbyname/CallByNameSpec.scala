package com.maple.scala.callbyname

import java.util.concurrent.TimeUnit

import com.maple.Logging

object CallByNameSpec extends Logging {
    def main(args: Array[String]): Unit = {
        val argMap = ArgumentParser.parse(args)
        val date: String = argMap("code")

        failBackLoop({ () ⇒ process(date.toInt) })

        println("------------------------------------------------------------------------")

        failBackLoop2(process(date.toInt))

        println("highFuc1------------------------------------------------------------------------")
        val s = highFuc1(value ⇒ {
            println(s"stringToInt value : $value")
            value.toInt
        })("2")

        println(s)
    }

    def process(code: Int): Either[Exception, Result] = {
        try {
            println(s"code => $code")
            code match {
                case 500 ⇒ throw new RuntimeException("500 error.")
                case _ ⇒
            }
            val result = Result(200, "SUCCESS")
            Thread.sleep(1000)
            Right(result)
        } catch {
            case e: Exception ⇒
                log.error(e.getMessage, e)
                Left(e)
        }
    }

    def stringToInt(value: String): Int = {
        println(s"stringToInt value : $value")
        value.toInt
    }

    def failBackLoop[T](f: () ⇒ Either[Exception, T], count: Int = 1): Option[T] = {
        log.info(s"\n 记录第${count}次请求\n")

        val result = f()
        println(result)

        if (result.isRight) Option(result.right.get)
        else if (count >= 5) {
            log.error(s"try $count times up,got failed")
            None
        }
        else {
            log.info(s"FailBack 重试,第 $count 次重试.")
            TimeUnit.SECONDS.sleep(1)
            failBackLoop(f, count + 1)
        }
    }

    def failBackLoop2[T](f: Either[Exception, T], count: Int = 1): Option[T] = {
        log.info(s"\n[PivotReportAgent] ===================== 记录第${count}次请求 ==================\n")
        if (f.isRight) Option(f.right.get)
        else if (count >= 5) {
            log.error(s"try $count times up,got failed")
            None
        }
        else {
            log.info(s"FailBack 重试,第 $count 次重试.")
            TimeUnit.SECONDS.sleep(1)
            failBackLoop2(f, count + 1)
        }
    }

    def failBackLoop3[T](f: String ⇒ Either[Exception, T], count: Int = 1): Option[T] = {
        log.info(s"\n[PivotReportAgent] ===================== 记录第${count}次请求 ==================\n")
        val result = f("2")
        if (result.isRight) Option(result.right.get)
        else if (count >= 5) {
            log.error(s"try $count times up,got failed")
            None
        }
        else {
            log.info(s"FailBack 重试,第 $count 次重试.")
            TimeUnit.SECONDS.sleep(1)
            failBackLoop3(f, count + 1)
        }
    }

    def highFuc1(f: String ⇒ Int) = {
        f
    }

    case class Result(code: Int, msg: String)

}
