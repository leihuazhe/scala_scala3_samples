package com.maple.scala3.turtoral.variance

import java.util

/**
  * Contravariance
  *
  * @author leihz
  * @version 1.0.0
  * @since 2022/07/10 18:24
  */
object Contravariance:
  class Fruit()

  class Apple() extends Fruit()

  class RedApple() extends Apple()


  def doFruit(fruit: util.ArrayList[Fruit]) = {
    fruit forEach println
  }

  def doFruit2[T <: Fruit](fruit: util.ArrayList[T]) = {
    fruit forEach println
  }

  //copy方法限制了拷贝源src必须是T或者是它的子类，
  // 而拷贝目的地dest必须是T或者是它的父类，这样就保证了类型的合法性。
  def copy[S, D >: S](src: List[S], dest: List[D]): Unit = {

  }

  //逆变,限制了下界, T 必须为 Apple 或其超类
  def writeTo[T >: Apple](apples: List[T]): Unit = {
  }


  @main def test1(): Unit = {
    val apples: util.ArrayList[Apple] = new util.ArrayList[Apple]()
    val redApples: util.ArrayList[RedApple] = new util.ArrayList[RedApple]()
    val fruits: util.ArrayList[Fruit] = new util.ArrayList[Fruit]()

    //    doFruit(apples)
    //    doFruit(redApples)
    doFruit(fruits)

    doFruit2(apples)
    doFruit2(redApples)
    doFruit2(fruits)


  }