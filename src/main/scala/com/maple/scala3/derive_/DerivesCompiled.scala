/*


package com.maple.scala3.derive_
@scala.annotation.internal.SourceFile("src/main/scala/com/maple/scala3/derive_/DerivedMain.scala")
object DerivesCompiled {
  @scala.annotation.internal.Child
  @scala.annotation.internal.Child
  sealed abstract class Opt[+T]() extends scala.reflect.Enum {
    import Opt.{Sm, Nn}
  }
  //enum 属于 Mirror 的 Sum 子类 subtype.
  object Opt extends scala.AnyRef with scala.deriving.Mirror.Sum {
    final case class Sm[+T](t: T) extends Opt[T] {
      override def hashCode(): scala.Int = scala.runtime.ScalaRunTime._hashCode(Sm.this)

      override def equals(x$0: scala.Any): scala.Boolean = Sm.this.eq(x$0.$asInstanceOf$[java.lang.Object]).||(x$0 match {
        case x$0: Opt.Sm[Sm.this.T]@scala.unchecked =>
          Sm.this.t.==(`x$0₂`.t)
        case _ =>
          false
      })

      override def toString(): java.lang.String = scala.runtime.ScalaRunTime._toString(Sm.this)

      override def canEqual(that: scala.Any): scala.Boolean = that.isInstanceOf[Opt.Sm[Sm.this.T]@scala.unchecked]

      override def productArity: scala.Int = 1

      override def productPrefix: scala.Predef.String = "Sm"

      override def productElement(n: scala.Int): scala.Any = n match {
        case 0 =>
          Sm.this._1
        case _ =>
          throw new java.lang.IndexOutOfBoundsException(n.toString())
      }

      def ordinal: scala.Int = 0
    }
    // object 属于 Mirror 的 Product 子类 subtype.
    object Sm extends scala.AnyRef with scala.deriving.Mirror.Product {
      override def toString: java.lang.String = "Sm"

      type MirroredMonoType

      def fromProduct(`x$0₃`: scala.Product): MirroredMonoType = new Opt.Sm[scala.Any](`x$0₃`.productElement(0))
    }

    val Nn: Opt[scala.Nothing] = {
      final class $anon() extends Opt[scala.Nothing] with _root_.scala.runtime.EnumValue with scala.deriving.Mirror.Singleton {
        def readResolve(): scala.AnyRef = Opt.fromOrdinal($anon.this.ordinal)

        override def productPrefix: scala.Predef.String = "Nn"

        override def toString(): java.lang.String = "Nn"

        override def ordinal: scala.Int = 1
      }

      (new $anon(): Opt[scala.Nothing] & scala.runtime.EnumValue)
    }

    def fromOrdinal(ordinal: scala.Int): Opt[_ >: scala.Nothing <: scala.Any] = ordinal match {
      case 1 =>
        this.Nn
      case _ =>
        throw new java.util.NoSuchElementException(ordinal.toString())
    }

    def derived$Eq[T](implicit `x$0₄`: Eq[T]): Eq[Opt[T]] = Eq.derived[Opt[T]](Opt.$asInstanceOf$[scala.deriving.Mirror {
      type MirroredType >: Opt[T] <: Opt[T]
      type MirroredMonoType >: Opt[T] <: Opt[T]
      type MirroredElemTypes >: scala.Nothing <: scala.Tuple
    } & scala.deriving.Mirror.Sum {
      type MirroredMonoType >: Opt[T] <: Opt[T]
      type MirroredType >: Opt[T] <: Opt[T]
      type MirroredLabel >: "Opt" <: "Opt"
    } {
      type MirroredElemTypes >: scala.*:[Opt.Sm[T], scala.*:[Opt.Nn, scala.Tuple$package.EmptyTuple]] <: scala.*:[Opt.Sm[T], scala.*:[Opt.Nn, scala.Tuple$package.EmptyTuple]]
      type MirroredElemLabels >: scala.*:["Sm", scala.*:["Nn", scala.Tuple$package.EmptyTuple]] <: scala.*:["Sm", scala.*:["Nn", scala.Tuple$package.EmptyTuple]]
    }])

    type MirroredMonoType

    def ordinal(`x$0₅`: Opt.MirroredMonoType): scala.Int = `x$0₅`.ordinal
  }

  @scala.main def testDerived1(): scala.Unit = {
    import Opt.{_}




    val eqoi: Eq[Opt[Int]] = Opt.derived$Eq[scala.Int](Eq.given_Eq_Int)
    if (eqoi.eqv((Opt.Sm.apply[scala.Int](23): Opt[scala.Int]), (Opt.Sm.apply[scala.Int](23): Opt[scala.Int])).unary_!) scala.runtime.Scala3RunTime.assertFailed() else ()



    val eqoi2: Eq[Opt[scala.Double]] = Opt.derived$Eq[scala.Double](Eq.given_Eq_Double)
    if (eqoi2.eqv((Opt.Sm.apply[scala.Double](23.4): Opt[scala.Double]), (Opt.Sm.apply[scala.Double](23.4): Opt[scala.Double])).unary_!) scala.runtime.Scala3RunTime.assertFailed() else ()



    val eqoi3: Eq[Opt[All]] = Opt.derived$Eq[All](Eq.given_Eq_All)
    if (eqoi3.eqv((Opt.Sm.apply[All](new All("maple")): Opt[All]), (Opt.Sm.apply[All](new All("jeffrey")): Opt[All])).unary_!.unary_!) scala.runtime.Scala3RunTime.assertFailed() else ()
  }
}

 */