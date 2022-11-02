package sk.stuba.fei.api.mobv.zadanie.fragments.listpubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import sk.stuba.fei.api.mobv.zadanie.adapter.WebPubAdapter
import sk.stuba.fei.api.mobv.zadanie.data.WebPubsViewModel
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentListWebPubsBinding

class ListWebPubsFragment : Fragment() {
    private val viewModel: WebPubsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListWebPubsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.webPubsRecyclerView.adapter = WebPubAdapter()

        return binding.root
    }
}