//package source
//
//import com.maple.scala3.derive2.Equal
//
//sealed abstract class Opt[+T]() extends scala.reflect.Enum {
//
//  import Opt.{None, Some}
//
//}
//
//object Opt extends scala.AnyRef with scala.deriving.Mirror.Sum {
//  final case class Some[+T](t: T) extends Opt[T] {
//    override def hashCode(): scala.Int = scala.runtime.ScalaRunTime._hashCode(Some.this)
//
//    override def equals(x$0: scala.Any): scala.Boolean = Some.this.eq(x$0.$asInstanceOf$[java.lang.Object]).||(x$0 match {
//      case x$0: Opt.Some[Some.this.T]@scala.unchecked =>
//        Some.this.t.==(`x$0₂`.t)
//      case _ =>
//        false
//    })
//
//    override def toString(): java.lang.String = scala.runtime.ScalaRunTime._toString(Some.this)
//
//    override def canEqual(that: scala.Any): scala.Boolean = that.isInstanceOf[Opt.Some[Some.this.T]@scala.unchecked]
//
//    override def productArity: scala.Int = 1
//
//    override def productPrefix: scala.Predef.String = "Some"
//
//    override def productElement(n: scala.Int): scala.Any = n match {
//      case 0 =>
//        Some.this._1
//      case _ =>
//        throw new java.lang.IndexOutOfBoundsException(n.toString())
//    }
//
//    def ordinal: scala.Int = 0
//  }
//
//  object Some extends scala.AnyRef with scala.deriving.Mirror.Product {
//
//    type MirroredMonoType
//
//    def fromProduct(`x$0₃`: scala.Product): Opt.Some.MirroredMonoType = new Opt.Some[scala.Any](`x$0₃`.productElement(0))
//  }
//
//  val None: Opt[scala.Nothing] = {
//    final class $anon() extends Opt[scala.Nothing] with _root_.scala.runtime.EnumValue with scala.deriving.Mirror.Singleton {
//      def readResolve(): scala.AnyRef = Opt.fromOrdinal($anon.this.ordinal)
//
//      override def productPrefix: scala.Predef.String = "None"
//
//      override def toString(): java.lang.String = "None"
//
//      override def ordinal: scala.Int = 1
//    }
//
//    (new $anon(): Opt[scala.Nothing] & scala.runtime.EnumValue)
//  }
//
//  def fromOrdinal(ordinal: scala.Int): Opt[_ >: scala.Nothing <: scala.Any] = ordinal match {
//    case 1 =>
//      this.None
//    case _ =>
//      throw new java.util.NoSuchElementException(ordinal.toString())
//  }
//
//  def derived$Equal[T](implicit `x$0₄`: Equal[T]): Equal[Opt[T]] =
//    Equal.derived[Opt[T]](
//      Opt.$asInstanceOf$[scala.deriving.Mirror {
//        type MirroredType >: Opt[T] <: Opt[T]
//        type MirroredMonoType >: Opt[T] <: Opt[T]
//        type MirroredElemTypes >: scala.Nothing <: scala.Tuple
//      } & scala.deriving.Mirror.Sum {
//        type MirroredMonoType >: Opt[T] <: Opt[T]
//        type MirroredType >: Opt[T] <: Opt[T]
//        type MirroredLabel >: "Opt" <: "Opt"
//      } {
//        type MirroredElemTypes >: scala.*:[Opt.Some[T], scala.*:[Opt.None, scala.Tuple$package.EmptyTuple]] <: scala.*:[Opt.Some[T], scala.*:[Opt.None, scala.Tuple$package.EmptyTuple]]
//        type MirroredElemLabels >: scala.*:["Some", scala.*:["None", scala.Tuple$package.EmptyTuple]] <: scala.*:["Some", scala.*:["None", scala.Tuple$package.EmptyTuple]]
//      }]
//    )
//
//  type MirroredMonoType
//
//  def ordinal(`x$0₅`: Opt.MirroredMonoType): scala.Int = `x$0₅`.ordinal
//}
