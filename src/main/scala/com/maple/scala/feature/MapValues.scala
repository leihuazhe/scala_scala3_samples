package com.maple.scala.feature

import com.maple.scala.feature.EngineType.EngineType
import play.api.libs.json.{JsArray, JsValue, Json}

object MapValues {

  def main(args: Array[String]): Unit = {
    val kvs = List(
      KV("dalao", "xxx-10", """[{"v":"在家","w":1,"l":"场景#位置#当前位置"},{"v":"在家","w":1,"l":"场景2#位置#当前x位置"}]""", Option(1)),
      KV("dalao", "xxx-9", """[{"v":"在家","w":1,"l":"场景#位置#当前位置"}]""", Option(2)),
      KV("dalao", "xxx-8", """[{"v":"在家","w":1,"l":"场景#位置#当前位置"}]""", Option(3)),
      KV("dalao", "xxx-9", """[{"v":"在家","w":1,"l":"场景#位置#当前位置"}]""", Option(4)),
      KV("dalao", "xxx-6", """[{"v":"在家","w":1,"l":"场景#位置#当前位置"}]""", Option(5)),
      KV("dalao", "xxx-5", """[{"v":"在家","w":1,"l":"场景#位置#当前位置"}]""", Option(6))
    )
    val engineResult = EngineResult(EngineType.Pegasus, "dalao", kvs)


    //    val res = filter(engineResult, List("当前位置", "当前x位置"))
    val res = filter(engineResult, List())

    res foreach {
      case (k, v) => println(s"key $k, value: ${v.toString()}")
    }

  }

  def filter(result: EngineResult, keywords: List[String]) = {
    result.kvs.flatMap {
      kv =>
        val resultValue = Set.newBuilder[(String, RealKV)]
        val jsValues = Json.parse(kv.value).as[JsArray].value
        jsValues.foreach {
          jsValue => {
            val label = (jsValue \ "l").as[String]
            if (keywords.isEmpty) resultValue += ((label, RealKV(kv.timestamp.get, jsValue)))
            else
              keywords.foreach {
                keyword =>
                  if (label.contains(keyword)) {
                    resultValue += ((label, RealKV(kv.timestamp.get, jsValue)))
                  }
              }
          }
        }
        resultValue.result()
    }.groupBy(_._1).mapValues(_.map(_._2))

  }

}


case class EngineResult(types: EngineType, id: String, kvs: List[KV])

case class KV(id: String, key: String, value: String, timestamp: Option[Long])


case class RealResult(label: String, realKvs: List[RealKV])

case class RealKV(timestamp: Long, value: JsValue)

object EngineType extends Enumeration {
  type EngineType = Value

  val Redis = Value(1, "redis")
  val Pegasus = Value(2, "pegasus")
  val HBase = Value(3, "hbase")
  val None = Value(4, "none")
}