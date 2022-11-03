package sk.stuba.fei.api.mobv.zadanie.service

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import sk.stuba.fei.api.mobv.zadanie.data.GetPubsBody
import sk.stuba.fei.api.mobv.zadanie.data.PubsRootObject

private const val BASE_URL = "https://data.mongodb-api.com/app/data-fswjp/endpoint/data/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PubsApiService {
    @Headers("api-key: KHUu1Fo8042UwzczKz9nNeuVOsg2T4ClIfhndD2Su0G0LHHCBf0LnUF05L231J0M")
    @POST("action/find")
    suspend fun getPubs(
        @Body body: GetPubsBody = GetPubsBody(
            collection = "bars",
            database = "mobvapp",
            dataSource = "Cluster0"
        )
    ): PubsRootObject
}

object PubsApi {
    val service: PubsApiService by lazy { retrofit.create(PubsApiService::class.java) }

    enum class PubsApiStatus { LOADING, ERROR, DONE }
}