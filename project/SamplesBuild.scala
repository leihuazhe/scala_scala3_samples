import sbt.Keys._
import sbt._
import com.typesafe.sbt.packager.archetypes._

object SamplesBuild extends Build with JavaAppKeys {

    object Deps {
        val commons_codec = "commons-codec" % "commons-codec" % "1.10"
        val spark_client = "org.apache.spark" %% "spark-sql" % "2.4.0"

        val play_json = "com.typesafe.play" %% "play-json" % "2.5.4"

        val scala_sql = "com.github.wangzaixiang" %% "scala-sql" % "2.0.7"
        val mysql = "mysql" % "mysql-connector-java" % "5.1.46"

        val logback = "ch.qos.logback" % "logback-classic" % "1.2.3"

    }

    lazy val `spark_samples` = (project in file("spark_samples"))
            .settings(
                name := "spark_samples",
                organization := "com.xiaomi",
                libraryDependencies ++= excludedDeps(
                )
            )

    lazy val `scala-samples` = (project in file("."))
            .settings(
                name := "scala_samples",
                libraryDependencies ++= excludedDeps(
                    Deps.commons_codec,
                    Deps.play_json,
                    Deps.scala_sql,
                    Deps.mysql,
                    Deps.logback
                ) ++ Seq(
                )
            ).dependsOn(`spark_samples`)
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
