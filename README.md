//scala3格式化 配置方式: https://scalameta.org/scalafmt/docs/configuration.html

## metals

metals completions not work in scala3,why?
- https://www.reddit.com/r/scala/comments/s9t702/metals_code_completion_not_working_as_of_jan_2022/


#### 本地调试:

    sbt -jvm-debug 5005 run

#### Marco 调试
    sbt -J-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005

#### 指定不同的配置文件:

    sbt -Dconfig.resource=application-private.conf -jvm-debug 5005 run

### 使用 build.sbt 来定义依赖关系

```scala
name := "scala-sum"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"

// https://mvnrepository.com/artifact/com.alibaba/fastjson
libraryDependencies += "com.alibaba" % "fastjson" % "1.2.7"

libraryDependencies += "com.github.wangzaixiang" %% "scala-sql" % "2.0.6"

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.9",
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,

  "com.h2database" % "h2" % "1.4.184" % "test",
  "junit" % "junit" % "4.12" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.3" % "test",
  "mysql" % "mysql-connector-java" % "5.1.38" % "test"
)
```

### Marco

- https://github.com/lampepfl/scala3-macro-tutorial