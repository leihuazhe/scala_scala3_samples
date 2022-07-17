package com.maple.scala3.matching

/** TypeA
  *
  * @author
  *   leihz
  * @version 1.0.0
  * @since 2022/07/15
  *   11:44
  */
object TypeA:

  trait Remapping

  case object ReEmpty extends Remapping

  case class ReCons[N1 <: String, N2 <: String, R <: Remapping](n1: N1, n2: N2, rest: R)
      extends Remapping

  type Remapped[X <: String, R <: Remapping] <: String = R match {
    case ReEmpty.type     ⇒ X
    case ReCons[X, n, _] => n
    case ReCons[_, _, rr] ⇒ Remapped[X, rr]
  }

  type Hlp[X <: String, Rest <: Tuple] = X *: Rest

  //  type AllRemapped[T <: Tuple, R <: Remapping] <: Tuple = T match {
  //    case Unit ⇒ Unit
  //    case s *: rest ⇒ s match {
  //      case String ⇒ Remapped[s, R] *: AllRemapped[rest, R]
  //    }
  //  }
  // Match type reduction
  //  type AllRemapped[T <: Tuple, R <: Remapping] <: Tuple = T match {
  //    case Unit => Tuple.type
  //    case Hlp[s, rest] => Remapped[s, R] *: AllRemapped[rest, R]
  //  }

  type AllRemapped[T <: Tuple, R <: Remapping] <: Tuple = T match
    case EmptyTuple => T
    case s *: rest =>
      s match
        case String => Remapped[s, R] *: AllRemapped[rest, R]

  //  inline def g(x: "a" | "c" | "f") <: String = inline x match {
  //    case "a" => "b"
  //    case "c" => "d"
  //    case "f" => "f"
  //  }

  @main def main1(): Unit =
    val x1: (Int, Int) = (1, 2)
    //    g("a"): "b"
    //    g("c"): "d"
    //    g("f"): "f"
    // g("x") // doesn't compile

    type RemapAtoBAdCtoD = ReCons["a", "b", ReCons["c", "d", ReEmpty.type]]
    val expectedToCompile1: Remapped["a", RemapAtoBAdCtoD] = "b"
    //    val expectedToCompile1: Remapped["a", RemapAtoBAdCtoD] = "a"

    val expectedToCompile2: Remapped["c", RemapAtoBAdCtoD] = "d"
    //    val expectedToCompile2: Remapped["c", RemapAtoBAdCtoD] = "c"

    //    val expectedToCompile3: Remapped["f", RemapAtoBAdCtoD] = "f"
    val expectedToCompile4: Remapped["a", ReEmpty.type] = "a"
    // above examples compile as expected

    // val expectedNotToCompile: Remapped["a", RemapAtoBAdCtoD] = "a"
    // above example doesn't compile as expected
    // I am trying to get following:
    type MyList = ("a", "c", "f")
    // val remapped: AllRemapped[MyList, RemapAtoBAdCtoD] = ("b", "d", "f")
    //    val remapped: AllRemapped[MyList, RemapAtoBAdCtoD]
    //    println(remapped)
    val remapped: AllRemapped[MyList, RemapAtoBAdCtoD] = ("b", "d", "f")
//    val remapped: AllRemapped[MyList, RemapAtoBAdCtoD] = ("a", "c", "f")
    println(remapped)
