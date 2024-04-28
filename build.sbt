
scalaVersion:="3.3.1"
val PekkoVersion = "1.0.2"
val PekkoHttpVersion = "1.0.1"
val pekkoDependencies= Seq(
  "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion,
  "org.apache.pekko" %% "pekko-http-spray-json" % PekkoHttpVersion,
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.13",

  "org.apache.pekko" %% "pekko-http-testkit" % PekkoHttpVersion % Test,
  "org.apache.pekko" %% "pekko-actor-testkit-typed" % PekkoVersion % Test,
  "org.apache.pekko" %% "pekko-stream-testkit" % PekkoVersion % Test,
  "org.scalatest" %% "scalatest" % "3.3.0-alpha.1" % Test
)

lazy val chatSytem  = (project in file("chat-system")).
  settings(
    inThisBuild(List(
      organization    := "com.example",
      scalaVersion    := "3.3.1"
    )),
    name := "chat-system",
    libraryDependencies ++=pekkoDependencies
  )
