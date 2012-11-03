name := "sortable_challenge"

version := "1.0.0"

scalaVersion := "2.9.2"

scalacOptions ++= Seq("-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "1.8" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

// need scalatest also as a build dependency: the build implements a custom reporter
libraryDependencies += "org.scalatest" %% "scalatest" % "1.8"
