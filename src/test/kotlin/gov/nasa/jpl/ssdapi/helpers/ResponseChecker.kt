package gov.nasa.jpl.ssdapi.helpers

import gov.nasa.jpl.ssdapi.constants.SsdApiResponseConstants.DATE_FORMAT
import gov.nasa.jpl.ssdapi.constants.SsdApiResponseConstants.SSD_API_RETURNED_FIELDS_ORDER_WITHOUT_BODY_AND_FULLNAME
import gov.nasa.jpl.ssdapi.constants.SsdApiResponseConstants.SSD_API_SOURCE
import gov.nasa.jpl.ssdapi.constants.SsdApiResponseConstants.SSD_API_VERSION
import gov.nasa.jpl.ssdapi.dto.SsdApiRequestDto
import gov.nasa.jpl.ssdapi.dto.SsdApiResponseDto
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import java.text.SimpleDateFormat

/**
 * Various helpers are stored in this class to check return values
 * */

fun checkSignature(responseFromSsdApi: Response) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)

    //check that source and version are equivalent to constants data
    assertThat(response.signature.source).isEqualTo(SSD_API_SOURCE)
    assertThat(response.signature.version).isEqualTo(SSD_API_VERSION)
}

fun checkFieldsOrder(responseFromSsdApi: Response) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)
    response.fields.forEachIndexed { i, field ->
        assertThat(field).isEqualTo(SSD_API_RETURNED_FIELDS_ORDER_WITHOUT_BODY_AND_FULLNAME[i])
    }
}

fun checkStatusCode(response: Response?, statusCode: Int) {
    assertThat(response?.statusCode).isEqualTo(statusCode)
}

fun checkReturnedDataFormats(responseFromSsdApi: Response) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)

    //check that 'data' size is equals to 'count' value
    assertThat(response.count.toInt()).isEqualTo(response.data.size)

    //check formats from data array
    response.data.forEach() { data ->
        //check data size
        assertThat(data.size).isEqualTo(SSD_API_RETURNED_FIELDS_ORDER_WITHOUT_BODY_AND_FULLNAME.size)

        //check des format
        assertThat(data[0]).isNotNull()
        assertThat(data[0]).matches("^[\\d]{4}[\\s][a-zA-Z0-9]+$|^[\\d]{5,10}$|^[a-zA-Z0-9]+$")

        //check orbit_id format
        assertThat(data[1]).isNotNull()
        assertThat(data[1]).matches("^\\d+$")

        //check jd format
        assertThat(data[2]).isNotNull()
        assertThat(data[2]).matches("^[\\d]+[.][\\d]+$")

        //check that data is not null
        assertThat(data[3]).isNotNull()

        //check dist, dist_min and dist_max formats
        val distRegex = "^[\\d]{1}+[.][\\d]{10,18}.{0,4}"
        assertThat(data[4]).isNotNull()
        assertThat(data[5]).isNotNull()
        assertThat(data[6]).isNotNull()
        assertThat(data[4]).matches(distRegex)
        assertThat(data[5]).matches(distRegex)
        assertThat(data[6]).matches(distRegex)

        //check v_rel and v_inf formats
        val vRegex = "^[\\d]{1,5}[.][\\d]+$"
        assertThat(data[7]).isNotNull()
        assertThat(data[7]).matches(vRegex)
        if (data[8] != null) {
            assertThat(data[8]).matches(vRegex)
        }
        //check t_sigma_f format
        assertThat(data[9]).isNotNull()
        assertThat(data[9]).matches("^[<][\\s][\\d]{2}[:][\\d]{2}$|^[\\d]{2}[:][\\d]{2}$|^[>][\\s][\\d]{2}[:][\\d]{2}$|^[\\d]{1,5}[_][\\d]{1,5}[:][\\d]{1,5}$")

        //check h
        if (data[10] != null) {
            assertThat(data[10]).matches("^[\\d]{2,5}[.][\\d]+$|[\\d]{2,5}[.]$")
        }
    }
}

fun checkDateMinAndDateMax(requestDto: SsdApiRequestDto, responseFromSsdApi: Response) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)
    response.data.forEach() { data ->
        val dateFormat = SimpleDateFormat(DATE_FORMAT)
        val dateOutputFormat = SimpleDateFormat("YYYY-MM-DD")
        val date = dateFormat.parse(data[3])
        assertThat(date.after(dateOutputFormat.parse(requestDto.`date-min`)))

        //check that data not > than date-max
        if (requestDto.`date-max` != null) {
            assertThat(date.before(dateOutputFormat.parse(requestDto.`date-max`)))
        }
    }
}

fun checkThatDataSortByDist(responseFromSsdApi: Response) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)

    var array = ArrayList<Double>()
    response.data.forEach() { data ->
        array.add(data[4].toDouble())
    }
    assertThat(array.zipWithNext { s1, s2 -> s1 <= s2 }.all { it }).isTrue()
}

fun checkCount(responseFromSsdApi: Response, count: String) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)
    assertThat(response.count).isEqualTo(count)
}

fun checkDesAndOrbitId(responseFromSsdApi: Response, des: String, orbitId: String) {
    val response = responseFromSsdApi.`as`(SsdApiResponseDto::class.java)
    response.data.forEach() { data ->
        assertThat(data[0]).isEqualTo(des)
        assertThat(data[1]).isEqualTo(orbitId)
    }

}
