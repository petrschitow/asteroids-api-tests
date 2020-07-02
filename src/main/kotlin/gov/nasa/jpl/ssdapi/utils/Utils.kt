package gov.nasa.jpl.ssdapi.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun getRandom(from: Long = 0, to: Long = 1_000_000): Long {
    return (from..to).random()
}

fun fromDtoToMap(anyDtoObject: Any): Map<String, Any> {
    val json = Gson().toJson(anyDtoObject)
    val mapType = object : TypeToken<Map<String, Any>>() {}.type
    return Gson().fromJson(json, mapType)
}