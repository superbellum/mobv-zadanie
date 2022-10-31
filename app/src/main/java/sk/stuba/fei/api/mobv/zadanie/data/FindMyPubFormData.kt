package sk.stuba.fei.api.mobv.zadanie.data

import java.io.Serializable

data class FindMyPubFormData(
    val name: String,
    val pubName: String,
    val latitude: String,
    val longitude: String
) : Serializable
