package sk.stuba.fei.api.mobv.zadanie.data

import com.google.gson.annotations.SerializedName

data class PubsRootObject(
    @SerializedName("elements")
    val pubs: MutableList<Pub>? = null
)
