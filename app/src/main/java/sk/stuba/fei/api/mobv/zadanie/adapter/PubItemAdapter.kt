package sk.stuba.fei.api.mobv.zadanie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.Pub
import sk.stuba.fei.api.mobv.zadanie.fragments.listpubs.ListPubsFragmentDirections

class PubItemAdapter(
    private val context: Context,
    private val dataset: List<Pub>
) : RecyclerView.Adapter<PubItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pubNameTextView: TextView =
            view.findViewById(R.id.card_pub_name_text)
        val pubIdTextView: TextView = view.findViewById(R.id.card_pub_id_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.pub_list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val pub = dataset[position]
        pub.let {
            holder.pubNameTextView.text = it.tags?.name ?: "<name>"
            holder.pubIdTextView.text = it.id.toString()
        }

        // show details of current pub by clicking on current item view
        holder.itemView.setOnClickListener {
            it.findNavController().navigate(
                ListPubsFragmentDirections
                    .actionListPubsFragmentToPubDetailFragment(pub)
            )
        }
    }

    override fun getItemCount() = dataset.size
}
