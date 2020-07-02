package gov.nasa.jpl.ssdapi.steps

import gov.nasa.jpl.ssdapi.TestContext
import gov.nasa.jpl.ssdapi.clients.sendRequestToSsdApi
import gov.nasa.jpl.ssdapi.dto.SsdApiRequestDto
import gov.nasa.jpl.ssdapi.helpers.*
import io.cucumber.java8.En

class SsdApiSteps(private var testContext: TestContext) : En {

    init {

        When("I construct request where dist-max={string}, date-min={string}, date-max={string}, sort={string}")
        { distMax: String,
          dateMin: String,
          dateMax: String,
          sort: String ->

            if (dateMax != "null") {
                testContext.ssdApiRequestDto = SsdApiRequestDto(
                        `dist-max` = distMax,
                        `date-min` = dateMin,
                        `date-max` = dateMax,
                        sort = sort)
            } else {
                testContext.ssdApiRequestDto = SsdApiRequestDto(
                        `dist-max` = distMax,
                        `date-min` = dateMin,
                        sort = sort)
            }
        }

        When("I construct request where des={string}, date-min={string}, date-max={string}, dist-max={string}")
        { des: String,
          dateMin: String,
          dateMax: String,
          distMax: String ->

            testContext.ssdApiRequestDto = SsdApiRequestDto(
                    des = des,
                    `date-min` = dateMin,
                    `date-max` = dateMax,
                    `dist-max` = distMax
            )

        }

        And("I send request to the 'ssd-api.jpl.nasa.gov' url with parameters from Test Context") {
            testContext.response = sendRequestToSsdApi(testContext.ssdApiRequestDto!!)
        }

        And("I check signature") {
            checkSignature(testContext.response!!)
        }

        And("I check fields order") {
            checkFieldsOrder(testContext.response!!)
        }

        And("I check returned data formats") {
            checkReturnedDataFormats(testContext.response!!)
        }

        And("I check that des={string} and orbitId={string}") { des: String, orbitId: String ->
            checkDesAndOrbitId(testContext.response!!, des, orbitId)
        }

        And("I check that count={string}") { count: String ->
            checkCount(testContext.response!!, count)
        }

        And("I check that cd > 'date-min' and cd < 'date-max'") {
            checkDateMinAndDateMax(testContext.ssdApiRequestDto!!, testContext.response!!)
        }

        And("I check that data sort by 'dist' field") {
            checkThatDataSortByDist(testContext.response!!)
        }


    }


}