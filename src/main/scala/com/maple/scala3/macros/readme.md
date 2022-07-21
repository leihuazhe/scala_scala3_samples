如果e是一个表达式,
那么的 `{e}就是代表有类型的抽象语法树,代表e. 
如果T是一个类型, 
那么`[T]就是类型结构体,代表着T. 


关于这些的定义并不是最重要的,现在用的这样术语则是给出一些直观上的感觉.
相反来说, 
${e}则是执行这个表达式e, 产生一个有类型的抽象语法树或者是类型结构体, 并且在这个封闭的代码中绑定表达式(类型)的结果.



1. ${ Expr[T] }  -> T
```
inline def printlnForConstant() : Unit = ${ printlnForConstantImpl }

// Return expression to print a constant, only implicit Quotes is needed.
def printlnForConstantImpl(using q: Quotes) : Expr[Unit] = {
    '{ println("u1") }
}
```
- printlnForConstantImpl 返回的是 Expr[T]
- ${ printlnForConstantImpl } 将其转换为 T


2.如何查看 macro 编译的挂起文件

```
-Xprint-suspension
```