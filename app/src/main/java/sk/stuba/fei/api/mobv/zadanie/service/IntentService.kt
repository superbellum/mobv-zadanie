package sk.stuba.fei.api.mobv.zadanie.service

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

object IntentService {
    fun showPubOnMap(fragment: Fragment, latitude: String, longitude: String) {
        val uri = Uri.parse("geo:${latitude},${longitude}?z=18")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        fragment.startActivity(mapIntent)
    }
}