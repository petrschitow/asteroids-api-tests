package gov.nasa.jpl.ssdapi.steps

import gov.nasa.jpl.ssdapi.TestContext
import gov.nasa.jpl.ssdapi.helpers.checkStatusCode
import io.cucumber.java8.En

class CommonSteps(private var testContext: TestContext) : En {

    init {

        Then("I check that the status code is {int}") { statusCode: Int ->
            checkStatusCode(testContext.response, statusCode)
        }
    }
}