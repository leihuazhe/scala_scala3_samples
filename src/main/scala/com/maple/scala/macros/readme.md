## type check

在这个阶段如果发现 Macro 标记就会将这个 Macro 的功能实现程序



一个语法树（AST, Abstract Syntax Tree）

结构来在Macro的位置进行替代，然后从这个AST开始继续进行类型验证过程。


## Quasiquotes

> Quasiquotes are a neat notation that lets you manipulate Scala syntax trees with ease

`Quasiquotes` 是一个简洁的符号，可以让您轻松操作 `Scala` 语法树。

每次在 `q"..."` 中包装一段代码时，它将成为代表给定代码段的 `Tree` 。 
您可能已经注意到，这类似 `string interpolation`。 

Although they look like strings they operate on syntactic trees under the hood.

```scala
scala> println(tree match { case q"i am { a quasiquote }" => "it worked!" })
it worked!
```
每当您使用 `quasiquote` 匹配 `tree` 时，只要给定树的结构等同于您作为模式提供的树的结构，它就会匹配。
 您可以借助 `equalsStructure` 方法手动检查结构相等性：

#### Splicing 
> 拼接,编接

```scala
val ab = List(q"a", q"b")
val fab = q"f(..$ab)"
fab: universe.Tree = f(a, b)
```

`quasiquote` 是 Scala Macros 的一个重要部分，主要替代了原来 reflect api 中的 `reify` 功能，
具备更强大、方便灵活的处理AST功能。
`Scala Def Macros` 还提供了 Extractor Macros，结合Scala String Interpolation和模式匹配来提供compile time的extractor object生成.

## Extractor Macros

一个 `object` 加上它的 `apply` 可以当做 `method` 来调用。

```
implicit class UserInterpolate(sc: StringContext) {
     object usr {
       def apply(args: String*): Any = macro UserMacros.appl
       def unapply(u: User): Any = macro UserMacros.uapl
     }
}
```