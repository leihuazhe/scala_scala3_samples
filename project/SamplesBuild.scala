import sbt.Keys._
import sbt._
import com.typesafe.sbt.packager.archetypes._

object SamplesBuild extends Build with JavaAppKeys {

  object Deps {
    val commons_codec = "commons-codec" % "commons-codec" % "1.10"
    val spark_client = "org.apache.spark" %% "spark-sql" % "2.4.0"
  }

  /**
    * 在左边，name， version 和 scalaVersion 都是 键（keys）。
    * 一个键（key）就是一个 SettingKey[T]，TaskKey[T] 或者 InputKey[T] 的实例，
    * T 是期望的 value 的类型。 key 的类别将在下面讲解。
    *
    * <code>:=</code>  返回 Setting[T] 的
    */
  lazy val `scala-samples` = (project in file("."))
    .settings(
      name := "scala_samples",
      libraryDependencies ++= excludedDeps(
        Deps.commons_codec,
        Deps.spark_client
        
      ) ++ Seq(
      )
    )
  /* lazy val va_root = (project in file("."))
     .aggregate(`virtual-assistant`, `label-cloud-manager`)*/


  def excludedDeps(deps: ModuleID*) = {
    deps.toSeq map {
      _.exclude("org.antlr", "stringtemplate")
        .exclude("org.antlr", "antlr-runtime")
        .exclude("org.slf4j", "slf4j-log4j12")
        .exclude("org.apache.hbase", "hbase")
        .exclude("org.jboss.netty", "netty")
        .exclude("com.xiaomi.data", "data-platform-spec")
        .exclude("com.xiaomi.data", "data-platform-spec-galaxy")
        .exclude("com.xiaomi.infra", "scribe-log4j")
        .exclude("com.xiaomi", "xiaomi-thrift-shared")
        .exclude("com.xiaomi", "xiaomi-thrift-api")
        .exclude("com.xiaomi", "micloud-infra-shared-swift")
        .exclude("org.elasticsearch.client", "transport")
        .exclude("org.apache.httpcomponents", "httpclient")
        .exclude("org.jboss.netty", "netty")
    }
  }

  resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
}
