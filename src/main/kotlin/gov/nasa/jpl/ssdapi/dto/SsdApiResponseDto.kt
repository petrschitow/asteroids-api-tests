package gov.nasa.jpl.ssdapi.dto

data class SsdApiResponseDto(
        var signature: Signature,
        var count: String,
        var fields: List<String>,
        var data: List<List<String>>

) {

    data class Signature(
            var version: String,
            var source: String
    )


}