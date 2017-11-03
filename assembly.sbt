
assemblyMergeStrategy in assembly := {
  case PathList("org", "apache", "spark", _*)         => MergeStrategy.first
  case PathList("org.codehaus.janino", _*)         => MergeStrategy.first
  case PathList("org", "apache", "commons", _*)         => MergeStrategy.first
  case PathList("org.codehaus.hadoop", _*)         => MergeStrategy.first
  case PathList("org","aopalliance", _*)         => MergeStrategy.first
  case PathList("javax", _*)         => MergeStrategy.first
  case PathList("org", "apache", "hadoop", _*)         => MergeStrategy.first
  case PathList("overview.html", _*)         => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

