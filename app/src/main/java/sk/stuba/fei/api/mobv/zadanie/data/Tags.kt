package sk.stuba.fei.api.mobv.zadanie.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Tags(
    val amenity: String? = null,
    val name: String? = null,
    @Json(name = "opening_hours")
    @SerializedName("opening_hours")
    val openingHours: String? = null,
    val operator: String? = null,
    val level: String? = null,
    val source: String? = null,
    @Json(name = "outdoor_seating")
    @SerializedName("outdoor_seating")
    val outdoorSeating: String? = null,
    @Json(name = "internet_access")
    @SerializedName("internet_access")
    val internetAccess: String? = null,
    val website: String? = null,
    val phone: String? = null,
    val shop: String? = null,
    val smoking: String? = null,
    val wheelchair: String? = null,
    val email: String? = null
)
