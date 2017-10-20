package leifblaese.languagedetector.util

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession

object Spark {

  lazy val conf: SparkConf = new SparkConf()
    .setAppName("langDetect")
    .setMaster("local[4]")
    .set("spark.cores.max", "4")


  lazy val session: SparkSession = SparkSession
    .builder()
    .config(conf)
    .getOrCreate()

  lazy val sc: SparkContext = session.sparkContext

}
