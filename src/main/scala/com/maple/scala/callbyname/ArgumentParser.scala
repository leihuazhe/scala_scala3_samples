package com.maple.scala.callbyname

import scala.collection.mutable.HashMap

/**
  * Created by yunsima on 15-3-10.
  */
object ArgumentParser {
    def parse(args: Array[String]) = {
        var dic = new HashMap[String, String]();
        for (i <- 0 until(args.length, 2)) {
            if (args(i).startsWith("--") && i + 1 < args.length)
                dic += (args(i).substring(2) -> args(i + 1))
        }

        if (dic.size == 0)
            parseCmd(args)
        else
            dic
    }

    def parseCmd(args: Array[String]) = {
        var dic = new HashMap[String, String]();
        for (i <- 0 until args.length) {
            val split = args(i).split("=", 2)
            dic += (split(0) -> split(1))
        }

        dic
    }
}
