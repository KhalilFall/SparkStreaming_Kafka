package dev.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.{IntegerType, StringType, StructType}

object App {

  /**
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {
    // create spark session with settings
    System.setProperty("hadoop.home.dir","C:\\hadoop")//Vous devez Créer le dossier C:\hadoop\bin et y mettre le fichier winutils.exe que vous trouverez dans google
    val spark = SparkSession
      .builder
      .master("local[*]")
      .appName("Exemple")
      .getOrCreate()
    //spark.conf.set("spark.hadoop.parquet.enable.summary-metadata", false)
    spark.sparkContext.setLogLevel("ERROR")
    import spark.implicits._

    // create kafka streaming data frame
    val kafkaDS = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "votreTopic") // changer le nom du topic en votre nom de topic
      .option("startingOffsets","latest") //earliest
      .load()
      .selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")

    val schema: StructType = new StructType()
                        .add("date", StringType, true)
                        .add("nb_cas", StringType, true)
                        .add("variant", StringType, true)
                        .add("id_pays", IntegerType, true)

    val refoemated_df = kafkaDS.withColumn("struct_value", from_json(col("value"), schema))
    val explode_df = refoemated_df
                        .withColumn("id_pays", col("struct_value").getField("id_pays"))
                        .withColumn("nb_cas", col("struct_value").getField("nb_cas"))
                        .withColumn("variant", col("struct_value").getField("variant"))
                        .withColumn("date", col("struct_value").getField("date"))

    explode_df
      .writeStream
      .outputMode("update")
      .option("truncate", false)
      .format("console")
      .start()
      .awaitTermination()
  }

}

