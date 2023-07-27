package simulations

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef.http
import videogamedb.BaseSimulation
import videogamedb.commonmethods.ActionMethods._

import scala.concurrent.duration.DurationInt

class PerformanceScenario extends BaseSimulation{

  /**
   * Stress test
   */

  before {
    println(s"Running test with ${usersCount} users")
    println(s"Ramping users over ${rampDuration} seconds")
    println(s"Total Test duration: ${testDuration} seconds")
  }

  // 2. Scenario Definition
  val scn: ScenarioBuilder = scenario(s"Video game db stress test")
    .forever {
      exec(getAllVideoGames())
        .pause(duration = 2)
        .exec(authenticate())
        .pause(duration = 2)
        .exec(createNewGame())
        .pause(duration = 2)
        .exec(getSingleGame())
        .pause(duration = 2)
        .exec(getDeleteGame())
    }

  // 3. Load Scenario
  setUp(
    scn.inject(
      constantUsersPerSec(usersCount) during (rampDuration.seconds))
  )
    .protocols(httpConf(baseUrl = baseUrl))
    .maxDuration(testDuration.seconds)
    .assertions(
      //response time lte - less then
      global.responseTime.max.lte(2),
      //successful request greater then
      global.successfulRequests.percent.gt(99)
    )

  after {
    println("Stress test completed")
  }

}
