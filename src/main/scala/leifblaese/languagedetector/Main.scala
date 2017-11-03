package leifblaese.languagedetector

import java.util.InputMismatchException


/**
  * This is a reference implementation for a language detection pipeline.
  *
  * 1. Read language files from HDFS (or, in my case, local disk)
  * 2. Split data in train/test data
  * 3. Train a model on the train data
  * 4. Save model to disk
  * 5. Evaluate on the test data
  */
object Main {

  def parseArguments(list: List[String], argumentMap: Map[String, Any]): Map[String, Any] = {

    list match {
      case Nil => argumentMap
      case "--languages" :: value :: tail =>
        val supportedLanguages = value.split(",").toSeq
        parseArguments(tail, argumentMap + ("languages" -> supportedLanguages))

      case "--gramLengths" :: value :: tail =>
        val gramLengths = value.split(",").map(_.toInt).toSeq
        parseArguments(tail, argumentMap + ("gramLengths" -> gramLengths))

      case "--folder" :: value :: tail =>
        parseArguments(tail, argumentMap + ("folder" -> value))

      case x => throw new InputMismatchException(s"Could not parse input ${x.toString}")
    }
  }


  def main(args: Array[String]) = {

    val argumentMap = parseArguments(args.toList, Map[String, Any]())
    println(argumentMap)
    //    if (!argumentMap.contains("gramLengths")) {
    //      throw new Exception("Missing 'gramLenghts' argument")
    //    }
    //    if (!argumentMap.contains("languages")) {
    //      throw new Exception("Missing 'languages' argument")
    //    }
    //    if (!argumentMap.contains("folder")) {
    //      throw new Exception("Missing 'folder' argument")
    //    }


    val trainer = LanguageDetectionTraining(
      argumentMap("languages").asInstanceOf[Seq[String]],
      argumentMap("gramLengths").asInstanceOf[Seq[Int]],
      argumentMap("folder").asInstanceOf[String])


    trainer.train()

  }

}
