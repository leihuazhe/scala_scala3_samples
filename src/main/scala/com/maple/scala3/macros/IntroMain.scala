package com.maple.scala3.macros

import com.maple.scala3.macros.impl.MainImpl
import com.maple.scala3.macros.impl.Source2
import com.maple.scala3.macros.impl.Dummy
import com.maple.scala3.macros.tool.Source

@main def addOneTest() =
  val res = MainImpl.addOne(4)
  println(s"res: $res")

@main def showLineTest() =
  val res = Source.line()
  println(s"current line is: $res")
  println(s"current line is: ${Source.line()}")
  println(s"current line is: ${Source.line()}")
  println(s"current line is: ${Source.line()}")

//verify.BasicTestSuite

@main def addOneListTest() =
  val res = MainImpl.addOneList(5)
  println(s"addOneListTest res: $res")

@main def addOneToString() =
  val res = MainImpl.oneToString(102)
  println(s"addOneToString res: $res")

@main def addOneV2Test() =
  val res = MainImpl.addOneXv2(19)
  println(s"addOneV2Test: $res")

@main def getConfigTest() =
  val res = MainImpl.config
  println(s"get config: $res")
  val Compiler = MainImpl.config
  println(s"get config: $Compiler")

  println(s"get config: ${MainImpl.config}")

@main def typeEquals() =
  println("typeEquals: " + MainImpl.typeEq[java.lang.String, scala.Predef.String])
  println("typeEquals: " + MainImpl.typeEq[java.lang.Integer, Int])
  println("typeEquals: " + MainImpl.typeEq[java.lang.Boolean, Boolean])

@main def paramInfoTest() =
  val res = MainImpl.paramInfo[List[List[String]]]
  println("paramInfoTest: " + res)
  println("paramInfoTest: " + MainImpl.paramInfo[List[Int]])
  println("paramInfoTest: " + MainImpl.paramInfo[Boolean])

@main def dummyEchoTest() =
  // 编译期来实现方法 Dummy.echo("bar")
  val res = Source2.lines_bad(List("foo", Dummy.echo("bar")))
  println("dummyEchoTest: " + res)

@main def dummyEcho2Test() =
  // 编译期来实现方法 Dummy.echo("bar")
  val res = Source2.lines(List("foo", Dummy.echo("bar")))
  println("dummyEcho2Test: " + res)

@main def rightTest() =
  val res1 = MainImpl.right(1, "String")
  val res2 = MainImpl.right(0, "String")

  val res3 = MainImpl.right(null, "List[String]")
  val res4 = MainImpl.right("maple", "List[String]")

  println(s"res1: $res1")
  println(s"res2: $res2")
  println(s"res3: $res3")
  println(s"res4: $res4")

@main def lambdaTest() =
  val res = MainImpl.mkLambda(3)
  println(res)
  val res2 = MainImpl.mkLambda({

    val x = 3
    x * x / 2 + 1
  })
  println(res2)

@main def notImplementationMethod() =
  // MainImpl.<<=(14)
  //MainImpl.removedMethod("Maple")