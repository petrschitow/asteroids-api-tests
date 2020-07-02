package gov.nasa.jpl.ssdapi.config

import gov.nasa.jpl.ssdapi.constants.Endpoints
import io.restassured.builder.RequestSpecBuilder
import io.restassured.filter.log.LogDetail
import io.restassured.filter.log.RequestLoggingFilter
import io.restassured.filter.log.ResponseLoggingFilter
import io.restassured.http.ContentType
import io.restassured.specification.RequestSpecification

//ad loggers for requests
val requestSpecification: RequestSpecification =
        RequestSpecBuilder()
                .setBaseUri(Endpoints.SSD_API_URL)
                .setContentType(ContentType.JSON)
                .addFilter(RequestLoggingFilter(LogDetail.URI))
                .addFilter(ResponseLoggingFilter(LogDetail.BODY))
                .build()
