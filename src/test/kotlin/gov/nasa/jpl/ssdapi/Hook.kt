package gov.nasa.jpl.ssdapi

import io.cucumber.java8.En

class Hook(private var testContext: TestContext) : En {

    init {
        After { ->
            println("Tests completed")
        }
    }

}