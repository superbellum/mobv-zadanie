package sk.stuba.fei.api.mobv.zadanie.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sk.stuba.fei.api.mobv.zadanie.web.PubsApi

enum class PubsApiStatus { LOADING, ERROR, DONE }

class WebPubsViewModel : ViewModel() {

    private val _status = MutableLiveData<PubsApiStatus>()
    val status: LiveData<PubsApiStatus> = _status

    private val _pubs = MutableLiveData<List<Pub>?>()
    val pubs: LiveData<List<Pub>?> = _pubs

    init {
        getWebPubs()
    }

    private fun getWebPubs() {
        viewModelScope.launch {
            _status.value = PubsApiStatus.LOADING
            try {
                _pubs.value = PubsApi.service.getPubs().pubs
                _status.value = PubsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = PubsApiStatus.ERROR
                // todo: maybe load from local cache (JSON, DB) if cannot retrieve from web?
                _pubs.value = listOf()
            }
        }
    }

}