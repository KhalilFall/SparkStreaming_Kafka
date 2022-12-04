package com.sonatel.reporting

import java.util.Properties
import org.apache.kafka.clients.consumer.KafkaConsumer

import java.time.Duration
import scala.collection.JavaConverters._
object KafkaConsumer extends App{

    val props:Properties = new Properties()
    props.put("group.id", "test3")
    props.put("bootstrap.servers","localhost:9092")
    props.put("key.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer",
      "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("auto.offset.reset", "earliest")//latest, none
  val consumer = new KafkaConsumer(props)
    val topics = List("testTopic")
    try {
      consumer.subscribe(topics.asJava)
      while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record <- records.asScala) {
          println("Topic: " + record.topic() +
            ",Key: " + record.key() +
            ",Value: " + record.value() +
            ", Offset: " + record.offset() +
            ", Partition: " + record.partition())
        }
      }
    }catch{
      case e:Exception => e.printStackTrace()
    }finally {
      consumer.close()
    }


}

