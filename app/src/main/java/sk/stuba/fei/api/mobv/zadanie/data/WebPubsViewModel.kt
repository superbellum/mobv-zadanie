package sk.stuba.fei.api.mobv.zadanie.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sk.stuba.fei.api.mobv.zadanie.service.NotificationService.notifyToast
import sk.stuba.fei.api.mobv.zadanie.service.PubsApi
import sk.stuba.fei.api.mobv.zadanie.service.PubsApi.PubsApiStatus
import sk.stuba.fei.api.mobv.zadanie.service.PubsApi.PubsApiStatus.*

class WebPubsViewModel(
    application: Application
) : AndroidViewModel(application), IRemovePub, ISortPubs {
    private val context = getApplication<Application>().applicationContext

    private val _status = MutableLiveData<PubsApiStatus>()
    val status: LiveData<PubsApiStatus> = _status

    private val _pubs = MutableLiveData<MutableList<Pub>?>()
    val pubs: LiveData<MutableList<Pub>?> = _pubs

    init {
        getWebPubs()
    }

    override fun removePub(pub: Pub) = _pubs.value?.remove(pub) ?: false

    override fun sortPubs(direction: ISortPubs.SortDirection) {
        when (direction) {
            ISortPubs.SortDirection.ASC -> _pubs.value?.sortBy { it.tags?.name }
            ISortPubs.SortDirection.DESC -> _pubs.value?.sortByDescending { it.tags?.name }
        }
    }

    fun getWebPubs() {
        viewModelScope.launch {
            _status.value = LOADING
            try {
                _pubs.value = PubsApi.service.getPubs().pubs
                _status.value = DONE
                notifyToast(context, "Retrieved ${_pubs.value?.size ?: 0} pubs from server")
            } catch (e: Exception) {
                _status.value = ERROR
                // todo: maybe load from local cache (JSON, DB) if cannot retrieve from web?
                _pubs.value = mutableListOf()
                notifyToast(context, "Error retriveing pubs: ${e.localizedMessage}")
            }
        }
    }
}