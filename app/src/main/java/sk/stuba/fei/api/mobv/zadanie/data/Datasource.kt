package sk.stuba.fei.api.mobv.zadanie.data

import com.google.gson.Gson
import java.io.InputStream


object Datasource {
    lateinit var pubs: MutableList<Pub>

    fun loadPubs(inputStream: InputStream) {
        pubs = Gson()
            .fromJson(inputStream.bufferedReader(), PubsRootObject::class.java)
            ?.pubs ?: mutableListOf()
    }
}