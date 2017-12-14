import Dependencies._

lazy val root = (project in file(".")).
  settings(
    name := "advent-of-code-2017",
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.4",
      version      := "0.1.0-SNAPSHOT"
    )),
    libraryDependencies += scalaTest % Test
  )
