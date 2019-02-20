package com.maple.scala.map

import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

import scala.annotation.tailrec

case class KV(key: String, value: String, timestamp: Long)

object Map1 {

  def main(args: Array[String]): Unit = {
    filter(List("maple"))
  }

  def filter(keywords: List[String]) = {

    //对结果进行过滤
    /*val conditions = tagIds.map(id => new String(id)).toList
    val filteredResult = result.map {
      r => {
        if (filterCondition(r._1, conditions, 0) == 1) {
          Option(r)
        } else None
      }
    }.filter(_.isDefined).map(_.get).toMap

    val kvs = filteredResult.map {
      kv =>
        val sortKey = kv._1
        val currentTimestamp = sortKey match {
          case path"$_-$ts" => Long.MaxValue - ts.toLong
        }
        KV("", kv._1, kv._2, Option(currentTimestamp))
    }.toList*/
    val kvs = List(
      KV("xx-0001", """[{"id":1,"l":"maple"}]""", 1000L),
      KV("xx-0002", """[{"id":2,"l":"maple"}]""", 2000L),
      KV("xx-0003", """[{"id":3,"l":"maple"}]""", 3000L),
      KV("xx-0004", """[{"id":4,"l":"maple"}]""", 4000L),
      KV("xx-0005", """[{"id":5,"l":"maple"}]""", 5000L)
    )

    val transfer1: Seq[(String, Long, JsValue)] = kvs.flatMap {
      kv =>
        val resultValue = Set.newBuilder[(String, Long, JsValue)]
        //parse 可能失败
        val jsValues = Json.parse(kv.value).as[JsArray].value

        jsValues.foreach {
          jsValue => {
            val label = (jsValue \ "l").as[String]
            val ts = kv.timestamp
            if (keywords.isEmpty) resultValue += ((label, ts, addJsValueField(ts, jsValue)))
            else
              keywords.foreach {
                keyword =>
                  if (label.contains(keyword)) {
                    val ts = kv.timestamp
                    resultValue += ((label, ts, addJsValueField(ts, jsValue)))
                  }
              }
          }
        }
        resultValue.result()
      //      }.groupBy(_._1).mapValues(_.map(_._2))
    }
    println(transfer1)


    val transfer2 = transfer1.groupBy(_._1).map {
      r =>
        val jsValues = r._2.sortWith((m, n) => m._2 > n._2).map(_._3)
        //只取前10条开路
        (r._1, jsValues.take(10))
    }

    println(transfer2)
  }

  private def addJsValueField(ts: Long, jsValue: JsValue): JsValue = {
    jsValue.as[JsObject] + ("ts" -> Json.toJson(ts))
  }

  def filterByCondition(key: String, conditionList: List[String], op: (String, String) => Boolean): Boolean = {
    @tailrec
    def filterBy(key: String, conditionList: List[String], index: Int, op: (String, String) => Boolean):Boolean = {
      if (index >= conditionList.length) false
      else if (op(key, conditionList(index))) true
      else filterBy(key, conditionList, index + 1, op)
    }

    filterBy(key, conditionList, 0, op)
  }


}
