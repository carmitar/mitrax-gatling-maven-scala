package videogamedb.support

import scala.util.Random

object HelperMethods {

  val rnd = new Random()

  def getProperty(propertyName: String, defaultValue: String): String = {
    Option(System.getenv(propertyName))
      .orElse(Option(System.getProperty(propertyName)))
      .getOrElse(defaultValue)
  }
}
