# asteroids-api-tests
Some tests for asteroids api

### Technologies that were used in the project:
   * Kotlin (main program language)
   * Junit (test-runner)
   * Rest-Assured (library for REST-requests)
   * Cucumber (BDD methodology)
   * PicoContainer (DI. Share state between steps)
   
### Running tests locally:
1. Install Java and Maven on your computer: <br/>
https://www.oracle.com/java/technologies/javase-downloads.html <br/>
https://maven.apache.org/install.html

2. Run tests locally:<br />
```mvn test``` - run all tests <br/>

### Run tests in Docker container: 
From the root run command: <br/>
```docker build -t asteroids-api -f APP-META/docker-config/Dockerfile .``` <br/>
```docker run asteroids-api```<br/>
or <br/>
```docker-compose up```

### Visual test reports: 
 After test execution you can find test reports in dir /target/cucumber <br/>
 Open file ```index.html``` in browser.
