package gov.nasa.jpl.ssdapi

import gov.nasa.jpl.ssdapi.dto.SsdApiRequestDto
import gov.nasa.jpl.ssdapi.dto.SsdApiResponseDto
import io.restassured.response.Response

class TestContext {

    var response: Response? = null
    var ssdApiRequestDto: SsdApiRequestDto? = null
}