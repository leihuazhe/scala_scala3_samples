package com.maple.scala3.wjson.simple

import com.maple.scala3.util.FileUtils

import wjson._

/**
  * Extract1
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/10 13:31
  */
object Extract1:

  val JSON_PATH_1 = "wjson/rule.json"

  case class Rule(distribution_type: String, value_names: List[String], distribution_buckets: List[Number], rule: List[AnyRef])


  @main def serialAndDeserial(): Unit = {
    val rule = Rule("1", List("1", "2"), List(1, 2), List(Nil))
    //val jsValue = rule.toJson

  }


  @main def extractFromJson1(): Unit =
    val json1 = FileUtils.read(JSON_PATH_1)
    //json1.to[Rule](json1)
    json1.parseJson match {
      case rejson"""
                  {
                    name: $name@_,
                    rule_expression: { custom_based_rule: { component_name: $component_name@_ } },
                    data_type: $data_type@_,
                    rule_expression/custom_based_rule/rule/distribution_buckets: $distribution_buckets@_,
                    rule_expression/custom_based_rule/rule: ${rule}@_
                  }
        """ ⇒
        println(s"name: $name")
        println(s"component_name: $component_name")
        println(s"data_type: $data_type")
        println(s"rule: $rule")
        println(s"distribution_buckets: $distribution_buckets")


      case _ ⇒ println("Not matched.")
    }


  @main def extractFromJson2(): Unit =
    val json1 = FileUtils.read(JSON_PATH_1)
    json1.parseJson match {
      case rejson"""
                  {
                    name: $name@_,
                    rule_expression: { custom_based_rule: { component_name: $component_name @ BASIC_MEASURE2 } },
                    data_type: $data_type@_,
                    rule_expression/custom_based_rule/rule/distribution_buckets: $distribution_buckets@_,
                    rule_expression/custom_based_rule/rule: ${rule}@_
                  }
        """ ⇒
        println(s"name: $name")
        println(s"component_name: $component_name")
        println(s"data_type: $data_type")
        println(s"rule: $rule")
        println(s"distribution_buckets: $distribution_buckets")
      case _ ⇒ println("Not matched.")
    }

