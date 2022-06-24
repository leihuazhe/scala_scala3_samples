//name := "scala_samples"
//version := "0.1"
//scalaVersion := "2.12.8"

//scalaVersion in ThisBuild := "2.11.7"


lazy val root = project
  .in(file("."))
  .settings(
    name := "scala_samples",
    description := "Example sbt project that compiles using Scala 3",
    version := "0.1.0",
    scalaVersion := "3.1.2",
    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test
  )
