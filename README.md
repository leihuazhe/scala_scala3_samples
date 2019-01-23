### 使用 build.sbt 来定义依赖关系
```scala
name := "scala-sum"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"

// https://mvnrepository.com/artifact/com.alibaba/fastjson
libraryDependencies += "com.alibaba" % "fastjson" % "1.2.7"

libraryDependencies +=  "com.github.wangzaixiang" %% "scala-sql" % "2.0.6"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.9",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,

  "com.h2database" % "h2" % "1.4.184" % "test",
  "junit" % "junit" % "4.12" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.3" % "test",
  "mysql" % "mysql-connector-java" % "5.1.38" % "test"
)
```