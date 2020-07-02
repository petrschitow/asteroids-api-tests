package gov.nasa.jpl.ssdapi.dto

data class SsdApiRequestDto(
        var `date-min`: String? = null,
        var `date-max`: String? = null,
        var `dist-min`: String? = null,
        var `dist-max`: String? = null,
        var `h-min`: String? = null,
        var `h-max`: String? = null,
        var `v-inf-min`: String? = null,
        var `v-inf-max`: String? = null,
        var `v-rel-min`: String? = null,
        var `v-rel-max`: String? = null,
        var `class`: String? = null,
        var pha: String? = null,
        var nea: String? = null,
        var comet: String? = null,
        var `nea-comet`: String? = null,
        var neo: String? = null,
        var kind: String? = null,
        var spk: String? = null,
        var des: String? = null,
        var body: String? = null,
        var sort: String? = null,
        var limit: String? = null,
        var fullname: String? = null
)
