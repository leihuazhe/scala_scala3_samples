//name := "scala_samples"
//version := "0.1"
//scalaVersion := "2.12.8"

//scalaVersion in ThisBuild := "2.11.7"


//lazy val root = project
//  .in(file("."))
//  .settings(
//    name := "scala_samples",
//    description := "Example sbt project that compiles using Scala 3",
//    version := "0.1.0",
//    scalaVersion := "3.1.2",
//    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
//  )

organization := "com.maple.scala"
name := "scala-samples"
version := "3.0.0"
scalaVersion := "3.1.3"

// scalacOptions := Seq( "-Yexplicit-nulls" )

// crossScalaVersions := Seq("2.11.12", "2.12.15")

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "1.7.9",
  // "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.github.jsqlparser" % "jsqlparser" % "1.2",
  "io.getquill" %% "quill-sql" % "3.18.0",
  "io.getquill" %% "quill-jdbc" % "3.18.0",
  "com.typesafe.play" %% "play-json" % "2.10.0-RC6",
  //"com.github.wangzaixiang" % "scala-sql_2.12" % "2.0.7",
  //uJson
  //"com.github.wangzaixiang" %% "wjson" % "0.1.0",

  "com.github.wangzaixiang" %% "wjson_sjs1" % "0.1.0-SNAPSHOT",
  // https://mvnrepository.com/artifact/org.scala-lang/scala3-compiler
  "org.scala-lang" %% "scala3-compiler" % scalaVersion.value,


  "com.h2database" % "h2" % "1.4.184" % "test",
  "junit" % "junit" % "4.12" % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.11" % "test",
  "ch.qos.logback" % "logback-core" % "1.2.11" % "test",
  "mysql" % "mysql-connector-java" % "5.1.38" % "test"

)
publishMavenStyle := true

javacOptions ++= Seq("-encoding", "UTF-8")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
//publishConfiguration := publishConfiguration.value.withOverwrite(true)



