
scalaVersion:="3.3.3"
libraryDependencies += "org.apache.pekko" %% "pekko-http" % "1.0.1"
val PekkoVersion = "1.0.2"
val PekkoHttpVersion = "1.0.1"
libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion
)