package sk.stuba.fei.api.mobv.zadanie.data

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class PubsRootObject(
    @Json(name = "documents") // when downloading from webserver
    @SerializedName("elements") // when loading from JSON file
    val pubs: MutableList<Pub>? = null
)
