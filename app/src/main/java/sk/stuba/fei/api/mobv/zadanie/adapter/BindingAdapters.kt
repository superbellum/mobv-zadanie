package sk.stuba.fei.api.mobv.zadanie.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.Pub
import sk.stuba.fei.api.mobv.zadanie.data.PubsApiStatus

@BindingAdapter("cardPubName")
fun bindPubName(textView: TextView, pub: Pub) {
    textView.text = pub.tags?.name ?: "<name>"
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Pub>?) {
    val adapter = recyclerView.adapter as WebPubAdapter
    adapter.submitList(data)
}

@BindingAdapter("pubApiStatus")
fun bindStatus(statusImageView: ImageView, status: PubsApiStatus?) {
    when (status) {
        PubsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PubsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        PubsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        else -> {}
    }
}