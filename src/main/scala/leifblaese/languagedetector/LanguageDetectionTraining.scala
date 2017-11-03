package leifblaese.languagedetector

import leifblaese.languagedetector.util.Spark
import org.apache.spark.ml.feature.languagedetection.LanguageDetector
import org.apache.spark.sql.catalyst.encoders.RowEncoder
import org.apache.spark.sql.{DataFrame, Dataset, Row}

object LanguageDetectionTraining {


  /**
    * Load json text files for a certain language from directory
    * @param jsonFile
    * @param language
    */
  def loadJsonFiles(jsonFile: String, language: String): Dataset[Wikitext] = {
    import Spark.session.implicits._

    println(s"Loading from $jsonFile")
    Spark
      .session
      .read
      .json(jsonFile)
      .map {
        row =>
          Wikitext(
            lang = language,
            id = row.getString(0),
            fulltext = row.getString(1),
            title = row.getString(2),
            url = row.getString(3))
      }
  }
}


case class LanguageDetectionTraining(
                                 languages: Seq[String],
                                 gramLenghts: Seq[Int],
                                 textFolder: String) {

  import LanguageDetectionTraining._

  def train() = {

    val trainingData = languages
      .map(language => loadJsonFiles(textFolder + language, language))
      .reduce(_ union _)

    val estimator = new LanguageDetector(
      supportedLanguages = languages,
      gramLenghts,
      languageProfileSize = 10000
    )


    val model = estimator.fit(trainingData)
    model.save("/user/lbl/langdetect/languageDetectionModel1")



  }
}