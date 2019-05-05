name := "scala-finch-circe"

version := "0.1"

scalaVersion := "2.12.8"

test in assembly := {}

mainClass in assembly := Some("FinchApp")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case "application.conf" => MergeStrategy.concat
  case "reference.conf" => MergeStrategy.concat
  case _ => MergeStrategy.first
}

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % "0.28.0",
  "com.github.finagle" %% "finchx-circe" % "0.28.0",
  "org.typelevel" %% "cats-effect" % "1.3.0"
)