lazy val commonSettings = Seq(
  organization := "streaming",
  version := "0.1.0",
  // set the Scala version used for the project.  2.11 isn't supported with Spark yet
  scalaVersion := "2.11.12"
)

val spark             = "org.apache.spark" % "spark-core_2.11" % "2.3.2"
val sparkStreaming    = "org.apache.spark" % "spark-streaming_2.11" % "2.3.2"
val sparkStreamKafka  = "org.apache.spark" % "spark-streaming-kafka-0-10_2.11" % "2.3.2"
val sparkSql = "org.apache.spark" %% "spark-sql" % "2.3.2"
val kafkaSql = "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.3.2"
val kafkaStreams = "org.apache.kafka" %% "kafka-streams-scala" % "2.3.1"



lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies += spark,
    libraryDependencies += sparkStreaming,
    libraryDependencies += sparkStreamKafka,
    libraryDependencies += sparkSql,
    libraryDependencies += kafkaSql,
    libraryDependencies += kafkaStreams ,
    libraryDependencies += "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.13.3"

  )