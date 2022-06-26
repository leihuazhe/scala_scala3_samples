//package com.maple.scala.xiaomi
//
///**
//  * Scala Runtime Reflection
//  */
//object TypeTagTest {
//
//  def main(args: Array[String]): Unit = {
//    test4()
//    println("-------test4-------test5----------")
//    test5()
//  }
//
//
//  def getTypeTag[T: ru.TypeTag](obj: T) = {
//    ru.typeTag
//  }
//
//  /**
//    * INSPECTING A RUNTIME TYPE (INCLUDING GENERIC TYPES AT RUNTIME)
//    *
//    * 与其他JVM语言一样，Scala的类型在编译时被擦除,这意味着如果您要检查某个实例的运行时类型，那么您可能无法访问Scala编译器在编译时可用的所有类型信息
//    *
//    * TypeTags可以被认为是将在编译时可用的 所有类型信息 传递到 运行时的对象,但是，重要的是要注意TypeTags总是由编译器生成。
//    * 只要使用需要TypeTag的隐式参数或上下文绑定，就会触发此生成。
//    *
//    * 这意味着通常只能使用隐式参数或上下文边界来获取TypeTag。
//    *
//    */
//  def test1(): Unit = {
//    val l = List(1, 2, 3)
//
//    val res = getTypeTag(l)
//
//    val theType = res.tpe
//
//    println(theType)
//
//    val symbols: Iterable[ru.Symbol] = theType.decls
//
//    symbols foreach println
//  }
//
//  /**
//    * INSTANTIATING A TYPE AT RUNTIME
//    */
//  def test2(): Unit = {
//    //First step, obtain a Mirror,which makes all classes and types available that are loaded by the current classloader, including class Person
//    val m: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
//
//    //The second step involves obtaining a ClassMirror for class Person using the reflectClass method.
//    // The ClassMirror provides access to the constructor of class Person.
//    val classPerson: ru.ClassSymbol = ru.typeOf[Person].typeSymbol.asClass
//    val cm: ru.ClassMirror = m.reflectClass(classPerson)
//
//    //The symbol for Persons constructor can be obtained using only the runtime universe ru
//    // by looking it up in the declarations of type Person.
//    val ctor: ru.MethodSymbol = ru.typeOf[Person].decl(ru.termNames.CONSTRUCTOR).asMethod
//
//    val ctorm: ru.MethodMirror = cm.reflectConstructor(ctor)
//
//    val p: Person = ctorm("maple").asInstanceOf[Person]
//
//    println(p)
//  }
//
//
//  /**
//    * ACCESSING AND INVOKING MEMBERS OF RUNTIME TYPES
//    */
//  def test3(): Unit = {
//    //In this example, we will attempt to get and set the shipped field of Purchase p, reflectively.
//    val p = Purchase("Jeff Lebowski", 23819, false)
//    println("before-> " + p)
//
//    //We now look up the declaration of the shipped field, which gives us a TermSymbol (a type of Symbol).
//    // We’ll need to use this Symbol later to obtain a mirror that gives us access to the value of this field (for some instance).
//    val m = ru.runtimeMirror(getClass.getClassLoader)
//    val shipTermSymbol: ru.TermSymbol = ru.typeOf[Purchase].decl(ru.TermName("shipped")).asTerm
//
//    //In order to access a specific instance’s shipped member,
//    // we need a mirror for our specific instance, p’s instance mirror, im.
//    // Given our instance mirror, we can obtain a FieldMirror for any TermSymbol representing a field of p’s type.
//    val im: ru.InstanceMirror = m.reflect(p)
//    val shippingFieldMirror: ru.FieldMirror = im.reflectField(shipTermSymbol)
//    shippingFieldMirror.set(true)
//
//    println("after-> " + p)
//  }
//
//
//  /**
//    * Runtime Classes in Java vs. Runtime Types in Scala
//    *
//    * 那些习惯使用Java反射在运行时获取 Java类实例 的人可能已经注意到，在Scala中，我们改为获取 运行时类型。
//    */
//  def test4(): Unit = {
//    //we create an instance of both C and D, meanwhile making type member T concrete (in both cases, String)
//    // meanwhile 与此同时
//    val c = new C {
//      type T = String
//    }
//    val d = new D {
//      type T = String
//    }
//
//    val r = c.getClass.isAssignableFrom(d.getClass)
//    println(r)
//  }
//
//  def test5(): Unit = {
//    val c = new C {
//      type T = String
//    }
//    val d = new D {
//      type T = String
//    }
//    //As we can see, we now get the expected result– d’s runtime type is indeed a subtype of c’s runtime type.
//    val res = m(d, c)
//    println(res)
//  }
//
//  def m[T: ru.TypeTag, S: ru.TypeTag](x: T, y: S): Boolean = {
//    val leftTag = ru.typeTag[T]
//    val rightTag = ru.typeTag[S]
//    leftTag.tpe <:< rightTag.tpe
//  }
//
//
//}
//
//
//case class Person(name: String)
//
//case class Purchase(name: String, orderNumber: Int, var shipped: Boolean)
//
////test4
//class E {
//  type T
//  val x: Option[T] = None
//}
//
//class C extends E
//
//class D extends C