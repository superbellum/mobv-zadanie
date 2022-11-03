package sk.stuba.fei.api.mobv.zadanie.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.ISortPubs.SortDirection
import sk.stuba.fei.api.mobv.zadanie.data.ISortPubs.SortDirection.ASC
import sk.stuba.fei.api.mobv.zadanie.data.ISortPubs.SortDirection.DESC
import sk.stuba.fei.api.mobv.zadanie.service.NotificationService.notifyToast

class JsonPubsViewModel(
    application: Application
) : AndroidViewModel(application), IRemovePub, ISortPubs {
    private val context = getApplication<Application>().applicationContext

    private val _pubs = MutableLiveData<MutableList<Pub>?>()
    val pubs: LiveData<MutableList<Pub>?> = _pubs

    init {
        loadPubs()
    }

    override fun removePub(pub: Pub) = _pubs.value?.remove(pub) ?: false

    override fun sortPubs(direction: SortDirection) {
        when (direction) {
            ASC -> _pubs.value?.sortBy { it.tags?.name }
            DESC -> _pubs.value?.sortByDescending { it.tags?.name }
        }
    }

    private fun loadPubs() {
        try {
            _pubs.value = Gson().fromJson(
                context.resources.openRawResource(R.raw.pubs).bufferedReader(),
                PubsRootObject::class.java
            )?.pubs ?: mutableListOf()
            notifyToast(context, "Loaded ${_pubs.value?.size ?: 0} pubs from JSON file")
        } catch (e: Exception) {
            _pubs.value = mutableListOf()
            notifyToast(context, "Could not load pubs from JSON file")
        }

    }
}