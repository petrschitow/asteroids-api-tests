package gov.nasa.jpl.ssdapi.clients

import gov.nasa.jpl.ssdapi.config.requestSpecification
import gov.nasa.jpl.ssdapi.dto.SsdApiRequestDto
import gov.nasa.jpl.ssdapi.utils.fromDtoToMap
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response


fun sendRequestToSsdApi(ssdApiRequestDto: SsdApiRequestDto): Response {
    return Given {
        spec(requestSpecification)
    } When {
        queryParams(fromDtoToMap(ssdApiRequestDto))
        get()
    } Extract {
        response()
    }
}
