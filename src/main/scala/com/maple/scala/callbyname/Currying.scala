package com.maple.scala.callbyname

/**
  * LinkedList
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/08/05 14:35
  */
case class LinkedList[T](head: T, tail: LinkedList[T])


object Currying {

  def cons[T](head: T): LinkedList[T] = {
    new LinkedList[T](head, null)
  }

  def cons[T](head: T, tail: LinkedList[T]): LinkedList[T] = {
    LinkedList[T](head, tail)
  }

  def listToString[T](list: LinkedList[T]): String = {
    if (list == null) {
      return ""
    }
    if (list.tail == null) {
      return list.head.toString
    }
    return list.head.toString + " " + listToString(list.tail)
  }

  //f: () ⇒ Either[Exception, T]
  //promotionFunction: Double => Double
  // (String, String) => String
  def myMap[T1, T2](fn: T1 => T2, list: LinkedList[T1]): LinkedList[T2] = {
    if (list == null) {
      return null
    }
    return cons(fn.apply(list.head), myMap(fn, list.tail))
  }

  def myReduce[T1, T2](fn: (T2, T1) ⇒ T2, accm: T2, list: LinkedList[T1]): T2 = {
    if (list == null) {
      return accm
    }
    myReduce(fn, fn.apply(accm, list.head), list.tail)
  }

  def myReduceRight[T1, T2](fn:(T1, ⇒T2) ⇒ T2, accm: T2, list: LinkedList[T1]): T2 = { // [BEGIN] YOUR CODE HERE
    if (list == null) {
      return accm
    }
    fn.apply(list.head, myReduceRight(fn, accm, list.tail))
    // [END] YOUR CODE HERE
  }

  //def myMap2[T1, T2](fn: Function[T1, T2], list: LinkedList[T1]): LinkedList[T2] = {
  //参考：https://stackoverflow.com/questions/28876368/scala-by-name-parameter-on-a-anonymous-function
  //https://stackoverflow.com/questions/19948598/by-name-parameter-vs-anonymous-function
  def myMap2[T1, T2](fn: T1 ⇒ T2, list: LinkedList[T1]): LinkedList[T2] = {
    if (list == null) {
      return null
    }
    val fun: (T1, => LinkedList[T2]) => LinkedList[T2] = (head, tail) ⇒ cons(fn.apply(head), tail)
    //myReduceRight((head: T1, tail: LinkedList[T2]) => cons(fn.apply(head), tail), null, list)
    myReduceRight(fun, null, list)
  }

  def main(args: Array[String]): Unit = {
    val exampleList: LinkedList[Integer] = cons(1, cons(2, cons(3, cons(4))))
    val plusOne: Function[Integer, Integer] = (x: Integer) => x + 1
    val xTimesTwoPlusY: (Integer, Integer) ⇒ Integer = (x: Integer, y: Integer) => x * 2 + y
    val printXAndReturnY: (Integer, Integer) ⇒ Integer = (x: Integer, y: Integer) => {
      System.out.println(x)
      y
    }
    val toString: Function[Integer, String] = (x: Integer) => x.toString
    val unfoldCalculation: (String, String) ⇒ String = (x: String, y: String) => "fn(" + x.toString + ", " + y.toString + ")"
    val printAndReturn: Function[Integer, Integer] = (x: Integer) => {
      def foo(x: Integer) = {
        System.out.println(x)
        x
      }

      foo(x)
    }
    System.out.println(listToString(exampleList) + " should be 1 2 3 4")
    System.out.println(listToString(myMap(plusOne, exampleList)) + " should be 2 3 4 5")
    //System.out.println(myReduce(xTimesTwoPlusY, 0, exampleList) + " should be 26")
    System.out.println(myReduce(unfoldCalculation, "accm", myMap(toString, exampleList)) + " should be fn(fn(fn(fn(accm, 1), 2), 3), 4)")
    //System.out.println(myReduceRight(xTimesTwoPlusY, 0, exampleList) + " should be 20")
    //System.out.println(myReduceRight(unfoldCalculation, "accm", myMap(toString, exampleList)) + " should be fn(1, fn(2, fn(3, fn(4, accm))))")
    System.out.println("Below should output 4 3 2 1 each on a separate line:")
    //myReduceRight(printXAndReturnY, 0, exampleList)
    System.out.println(listToString(myMap2(plusOne, exampleList)) + " should be 2 3 4 5")
    System.out.println("The two outputs below should be equal:")
    System.out.println("First output:")
    myMap(printAndReturn, exampleList)
    System.out.println("Second output:")
    val res: LinkedList[Integer] = myMap2(printAndReturn, exampleList)
    System.out.println(res)
  }
}