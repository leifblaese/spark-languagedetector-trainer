val libDeps: Seq[ModuleID] = Seq(
  "org.apache.spark" %% "spark-core" % "2.2.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "org.apache.spark" %% "spark-mllib" % "2.2.0",
  "org.specs2" % "specs2_2.11" % "3.7" % "test" pomOnly(),
  "com.typesafe" % "config" % "1.3.1",
  "lbl" %% "spark-languagedetector" % "0.0.1"
)


resolvers ++= Seq(
  Resolver.defaultLocal
)

lazy val common = Project("spark-languagedetector-trainer", file("."))
  .configs(IntegrationTest)
  .settings(
    name := "spark-languagedetector-trainer",
    scalaVersion := "2.11.11",
    organization := "lbl",
    libraryDependencies ++= libDeps,
    Defaults.itSettings,
    assemblyJarName in assembly := "spark-languagedetector-trainer.jar"
  )