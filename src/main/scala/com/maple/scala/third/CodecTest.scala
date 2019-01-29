package com.maple.scala.third

import java.nio.charset.Charset

import org.apache.commons.codec.binary.Base64

object CodecTest {

  def main(args: Array[String]): Unit = {
    val input = "maple is maple"

    val encode = Base64.encodeBase64(input.getBytes("UTF-8"))

    println(new String(encode))

    val decode = Base64.decodeBase64(encode)

    println(new String(decode))
  }
}
