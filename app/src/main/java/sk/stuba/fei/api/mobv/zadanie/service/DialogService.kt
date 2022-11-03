package sk.stuba.fei.api.mobv.zadanie.service

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

object DialogService {
    fun createAlertDialog(
        context: Context,
        title: String,
        message: String,
        isCancelable: Boolean = false,
        onOk: (DialogInterface, Int) -> Unit,
        onCancel: (DialogInterface, Int) -> Unit,
    ) = AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(isCancelable)
        .setPositiveButton("Ok", onOk)
        .setNegativeButton("Cancel", onCancel)
}