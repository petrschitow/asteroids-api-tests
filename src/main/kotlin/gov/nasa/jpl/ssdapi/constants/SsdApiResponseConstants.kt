package gov.nasa.jpl.ssdapi.constants


object SsdApiResponseConstants {

    const val SSD_API_SOURCE = "NASA/JPL SBDB Close Approach Data API"
    const val SSD_API_VERSION = "1.1"

    val SSD_API_RETURNED_FIELDS_ORDER_WITHOUT_BODY_AND_FULLNAME: ArrayList<String> = arrayListOf(
            "des",
            "orbit_id",
            "jd",
            "cd",
            "dist",
            "dist_min",
            "dist_max",
            "v_rel",
            "v_inf",
            "t_sigma_f",
            "h")

    const val DATE_FORMAT = "yyyy-MMM-dd HH:mm"
}