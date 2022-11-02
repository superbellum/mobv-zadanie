package sk.stuba.fei.api.mobv.zadanie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sk.stuba.fei.api.mobv.zadanie.data.Pub
import sk.stuba.fei.api.mobv.zadanie.databinding.WebPubItemBinding
import sk.stuba.fei.api.mobv.zadanie.fragments.listpubs.ListWebPubsFragmentDirections

class WebPubAdapter : ListAdapter<Pub, WebPubAdapter.WebPubViewHolder>(DiffCallback) {

    class WebPubViewHolder(
        private var binding: WebPubItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pub: Pub) {
            binding.pub = pub
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Pub>() {
        override fun areItemsTheSame(oldItem: Pub, newItem: Pub) = oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: Pub, newItem: Pub) =
            oldItem.tags?.name == newItem.tags?.name

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WebPubViewHolder(
        WebPubItemBinding.inflate(LayoutInflater.from(parent.context))
    )


    override fun onBindViewHolder(holder: WebPubViewHolder, position: Int) {
        val pub = getItem(position)
        holder.bind(pub)

        // show details of current pub by clicking on current item view
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(
                ListWebPubsFragmentDirections.actionListWebPubsToPubDetail(pub)
            )
        }
    }
}
