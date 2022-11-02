package sk.stuba.fei.api.mobv.zadanie.data

data class GetPubsBody(
    val collection: String? = null,
    val database: String? = null,
    val dataSource: String? = null
)