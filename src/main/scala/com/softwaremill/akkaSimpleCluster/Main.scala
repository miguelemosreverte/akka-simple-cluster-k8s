package com.softwaremill.akkaSimpleCluster

import akka.actor.ActorSystem
import akka.cluster.Cluster
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.management.scaladsl.AkkaManagement
import akka.management.cluster.bootstrap.ClusterBootstrap
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContextExecutor
import scala.util.{Failure, Success}


object Main extends App {

  lazy val config = ConfigFactory.load()
  implicit val system = ActorSystem("akka-simple-cluster")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  implicit val cluster = Cluster(system) // ??

  AkkaManagement(system).start()
  ClusterBootstrap(system).start()

  Http().bindAndHandle(
    complete(config.getString("application.api.hello-message")),
    config.getString("application.api.host"),
    config.getInt("application.api.port")
  )

  // exitSystemOnTerminate(system)

  private def exitSystemOnTerminate(actorSystem: ActorSystem): Unit = {

    //implicit val dispatcher: ExecutionContextExecutor = actorSystem.dispatcher
    val cluster = Cluster.get(actorSystem)

    cluster.registerOnMemberRemoved {
      actorSystem.whenTerminated onComplete {
        case Success(_) =>
          // logger.info(s"System exit after ${actorSystem.name} terminate.")
          System.exit(0)
        case Failure(_) =>
          // logger.warn(s"${actorSystem.name} abnormally termination")
          System.exit(-1)
      }
    }

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run(): Unit = {
        // logger.info("Downing {} from Cluster...", cluster.selfAddress)
        cluster.down(cluster.selfAddress)
        // actorSystem.registerOnTermination(logger.info("System shut down."))
        actorSystem.terminate()
      }
    })
  }
}