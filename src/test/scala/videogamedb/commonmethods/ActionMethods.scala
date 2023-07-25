package videogamedb.commonmethods

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object ActionMethods {

  val csvFeeder = csv(filePath = "data/gameCsvFile.csv").random

  def getAllVideoGames(): ChainBuilder = {
    exec(http(requestName = "Get all video games")
      .get("/videogame")
      .check(status.is(expected = 200)))
  }

  def authenticate(): ChainBuilder = {
    exec(http(requestName = "Authenticate")
      .post("/authenticate")
      .body(StringBody("{\n  \"password\": \"admin\",\n  \"username\": \"admin\"\n}"))
      check (jsonPath("$.token").saveAs("jwtToken")))
  }

  def createNewGame(): ChainBuilder = {
    feed(csvFeeder)
      .exec(http(requestName = "Create new game - #{gameName}")
        .post("/videogame/")
        .header("authorization", value = "Bearer #{jwtToken}")
        .body(ElFileBody(filePath = "bodies/newGameTemplate.json")).asJson)
  }

  def getSingleGame(): ChainBuilder = {
    exec(http(requestName = "Get single game - #{name}")
      .get("/videogame/#{gameId}")
      .check(jsonPath(path = "$.name").is(expected = "#{name}")))
  }

  def getDeleteGame(): ChainBuilder = {
    exec(http(requestName = "Delete game - #{name}")
      .delete("/videogame/#{gameId}")
      .header("authorization", value = "Bearer #{jwtToken}")
      .check(bodyString.is(expected = "Video game deleted")))
  }
}
