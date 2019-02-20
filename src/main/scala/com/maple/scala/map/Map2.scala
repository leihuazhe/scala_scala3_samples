package com.maple.scala.map

import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

import scala.annotation.tailrec


object Map2 {

  def main(args: Array[String]): Unit = {
    val s = List("9223370486367843807", "9223370486394185807", "9223370486375663807", "9223370486381982807")
      .map(r => Long.MaxValue - r.toLong)


    println(s)


    val list = List("abc", "abcd", "abcde", "abcdef", "abcdefg")
    val filterStr = List("bc")

    val res = list.map {
      l =>
        if (filterByCondition2(l, filterStr, (key, cond) => key.startsWith(cond)) == 1) {
          Option(l)
        } else {
          None
        }
    }.filter(_.isDefined).map(_.get)


    println(res)







    //    filter(List("maplex","maple"))
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
      KV("xx-0001", """[{"id":1,"l":"maplex"}]""", 1000L),
      KV("xx-0002", """[{"id":2,"l":"maple"}]""", 2000L),
      KV("xx-0003", """[{"id":3,"l":"maple"}]""", 3000L),
      KV("xx-0004", """[{"id":4,"l":"maple"}]""", 4000L),
      KV("xx-0005", """[{"id":5,"l":"maple"}]""", 5000L)
    )

    val transfer1 = kvs.flatMap {
      kv =>
        //parse 可能失败
        val jsValues = Json.parse(kv.value).as[JsArray].value

        jsValues.map {
          jsValue => {
            val label = (jsValue \ "l").as[String]
            val ts = kv.timestamp
            if (keywords.isEmpty) Option((label, ts, addJsValueField(ts, jsValue)))
            else if (filterCondition(label, keywords, 0) == 1) {
              Option((label, ts, addJsValueField(ts, jsValue)))
            } else None

          }
        }.filter(_.isDefined).map(_.get)
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

  @tailrec
  private def filterCondition(key: String, conditionList: List[String], index: Int): Int = {
    if (index >= conditionList.length) -1
    else if (key.startsWith(conditionList(index))) 1
    else filterCondition(key, conditionList, index + 1)
  }

  private def filterByCondition2(key: String, conditionList: List[String], op: (String, String) => Boolean): Int = {
    @tailrec
    def filterByCondition3(key: String, conditionList: List[String], index: Int, op: (String, String) => Boolean): Int = {
      if (index >= conditionList.length) -1
      else if (op(key, conditionList(index))) 1
      else filterByCondition3(key, conditionList, index + 1, op)
    }

    filterByCondition3(key, conditionList, 0, op)
  }


}
