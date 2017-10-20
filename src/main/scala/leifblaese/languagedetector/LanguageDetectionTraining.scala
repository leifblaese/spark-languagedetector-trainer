package leifblaese.languagedetector

import leifblaese.languagedetector.util.Spark

object LanguageDetectionTraining {


  /**
    * Load json text files for a certain language from directory
    * @param folder
    * @param language
    */
  def loadJsonFiles(folder: String, language: String) = {
    import Spark.session.implicits._
    val rawTextFiles = Spark.session.sparkContext.textFile(folder).toDS
    val jsonFiles = Spark.session.read.json(rawTextFiles)
  }
}


class LanguageDetectionTraining(
                                 languages: Seq[String],
                                 gramLenghts: Seq[Int],
                                 textFolder: String) {



}
