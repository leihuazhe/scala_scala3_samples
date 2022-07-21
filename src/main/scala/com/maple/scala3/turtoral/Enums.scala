package com.maple.scala3.turtoral

import wjson.{JsPattern, JsValue}

import java.util.Date

/** enum
  *
  * @author
  *   leihz
  * @version 1.0.0
  * @since 2022/07/10
  *   21:14
  */
//不带参数
enum Color:
  case Red, Green, Blue

//带参数
enum Color2(rgb: Int):
  case Red   extends Color2(0xff0000)
  case Green extends Color2(0x00ff00)
  case Blue  extends Color2(0x0000ff)
end Color2

enum DataType:
  case STRING(value: String)
  case BOOL(value: Boolean)
  case NUMBER(value: Number)
  case DATETIME(value: Date)

//companion object
object DataType:
  def unapplySeq(pat: JsPattern, js: JsValue): Option[List[Any]] = ???

//Test
@main def testEnum1(): Unit = {
  val colors = Color.values

  for (color <- colors) {
    println(s"${color} ordinal: ${color.ordinal}")
  }
  val color = Color.fromOrdinal(1)
  println(s"from color: $color")

  val red = Color.valueOf("Red")
  println(s"valueOf color: $red")

  color match {
    case Color.Red   ⇒
    case Color.Green ⇒
    case Color.Blue  ⇒
    case null        ⇒ println("Impossible")
  }

}

enum Planet(mass: Double, radius: Double):
  private final val G = 6.67300e-11

  def surfaceGravity = G * mass / (radius * radius)

  def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity

  case Mercury                                                  extends Planet(3.303e+23, 2.4397e6)
  case Venus                                                    extends Planet(4.869e+24, 6.0518e6)
  case Earth                                                    extends Planet(5.976e+24, 6.37814e6)
  case Mars                                                     extends Planet(6.421e+23, 3.3972e6)
  case Jupiter                                                  extends Planet(1.9e+27, 7.1492e7)
  case Saturn                                                   extends Planet(5.688e+26, 6.0268e7)
  case Uranus                                                   extends Planet(8.686e+25, 2.5559e7)
  @deprecated("refer to IAU definition of planet") case Neptune extends Planet(1.024e+26, 2.4746e7)
end Planet

//We could imagine that a library may use type class derivation to automatically provide an instance for Deprecations.
trait Deprecations[T <: reflect.Enum] {
  extension (t: T) def isDeprecatedCase: Boolean
}

object Planet {
  given Deprecations[Planet] with {
    extension (p: Planet) def isDeprecatedCase = p == Neptune
  }
}

//限制
/*enum Planet2(mass: Double, radius: Double):
  private final val (mercuryMass, mercuryRadius) = (3.303e+23, 2.4397e6)

  import Planet2._
  //无法访问成员变量
  case Mercury extends Planet2(mercuryMass, mercuryRadius)
  case Venus extends Planet2(venusMass, venusRadius)
  case Earth extends Planet2(Planet2.earthMass, Planet2.earthRadius)
end Planet2*/

object Planet2:
  private final val (venusMass, venusRadius) = (4.869e+24, 6.0518e6)
  private final val (earthMass, earthRadius) = (5.976e+24, 6.37814e6)

@main def testEnum2(): Unit = {
  val plant          = Planet.Neptune
  val deprecatedCase = plant.isDeprecatedCase

  println(s"is deprecated: $deprecatedCase")

}

//use for Java
enum Week extends Enum[Week] {
  case MONDAY, TUESDAY, WENSDAY
}
