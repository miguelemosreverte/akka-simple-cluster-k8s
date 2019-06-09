name := "akka-simple-cluster-k8s"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.8"

resolvers += Resolver.bintrayRepo("tanukkii007", "maven")

enablePlugins(JavaServerAppPackaging, DockerPlugin)

val akkaVersion = "2.5.23"
val akkaHttpVersion = "10.1.7"
val akkaManagementVersion = "1.0.0"

libraryDependencies ++=Seq(
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-sharding" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster-tools" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "com.typesafe.akka" %% "akka-discovery" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.lightbend.akka.management" %% "akka-management" % akkaManagementVersion,
  "com.lightbend.akka.management" %% "akka-management-cluster-http" % akkaManagementVersion,
  "com.lightbend.akka.management" %% "akka-management-cluster-bootstrap" % akkaManagementVersion,  
  "com.github.TanUkkii007" %% "akka-cluster-custom-downing" % "0.0.12"
)

libraryDependencies += "com.twitter" %% "chill-akka" % "0.9.3" // for kryo

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

dockerBaseImage := "openjdk:8"
dockerUsername := Some("softwaremill")