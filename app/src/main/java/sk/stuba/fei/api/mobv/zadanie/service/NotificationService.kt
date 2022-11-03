package sk.stuba.fei.api.mobv.zadanie.service

import android.content.Context
import android.widget.Toast

object NotificationService {
    fun notifyToast(context: Context, message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }
}