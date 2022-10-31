package sk.stuba.fei.api.mobv.zadanie.data

import java.io.Serializable

data class Pub(
    val type: String? = null,
    val id: Long,
    val lat: Double,
    val lon: Double,
    val tags: Tags? = null
) : Serializable
