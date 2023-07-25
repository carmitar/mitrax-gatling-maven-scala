# Gatling performance tasting with Scala

# Mitrax playground performance tests

#### Execution command explanation
1. The command should contain ENV where tests will be running: -Denv=staging
2. Inject a given number of users at once: DUSERS=10000
3. Inject a given number of users distributed evenly on a time window of a given duration: -DRAMP_DURATION=1, DDURATION=30

If those parameters are not defined, then default parameters will be injected

## Running the tests
1. Go to util -> Engine
2. Right-click on Run
3. Choose the index number of the test that you want to run
4. Enter the note and click enter

## Running from terminal Mac OS
mvn gatling:test -Dgatling.simulationClass="simulations.LoadOneThousandUsersPerSecond" -Denv=staging -DUSERS=10000 -DRAMP_DURATION=1 -DDURATION=30

## Running the tests on Windows environment
mvn gatling:test `-Dgatling.simulationClass="simulations.PerformanceScenario" `-Denv=dev `-DUSERS=10 `-DRAMP_DURATION=10 `-DDURATION=30

