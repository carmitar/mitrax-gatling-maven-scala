package videogamedb

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder
import videogamedb.util.Constants
import videogamedb.support.HelperMethods.getProperty

class BaseSimulation extends Simulation{

  val conf: Config = ConfigFactory.load()
  val env: String = getProperty("env", "dev")
  val baseUrl: String = conf.getString(s"${env.toLowerCase()}.baseUrl")

  def usersCount: Int = getProperty("USERS", "5").toInt

  def rampDuration: Int = getProperty("RAMP_DURATION", "10").toInt

  def testDuration: Int = getProperty("DURATION", "30").toInt

  def httpConf(baseUrl: String): HttpProtocolBuilder = {
    http
      .baseUrl(baseUrl)
      .acceptHeader(value = Constants.JSON_CONTENT_TYPE)
      .contentTypeHeader(value = Constants.JSON_CONTENT_TYPE)
  }

}
