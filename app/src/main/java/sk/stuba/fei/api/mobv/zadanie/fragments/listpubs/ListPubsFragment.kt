package sk.stuba.fei.api.mobv.zadanie.fragments.listpubs

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.adapter.JsonPubAdapter
import sk.stuba.fei.api.mobv.zadanie.data.ISortPubs.SortDirection.DESC
import sk.stuba.fei.api.mobv.zadanie.data.JsonPubsViewModel
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentListPubsBinding

class ListPubsFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentListPubsBinding
    private var sortPubsByNameAsc = true
    private val viewModel: JsonPubsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_pubs, container, false
        )

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        // Sets the adapter of the photosGrid RecyclerView
        binding.recyclerView.adapter = JsonPubAdapter()

        binding.recyclerView.setHasFixedSize(true)

        // menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.sort_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem) = when (menuItem.itemId) {
        R.id.sort_pub_by_name_menu_item -> {
            if (sortPubsByNameAsc) {
                viewModel.sortPubs()
            } else {
                viewModel.sortPubs(direction = DESC)
            }

            sortPubsByNameAsc = !sortPubsByNameAsc
            binding.recyclerView.adapter?.notifyDataSetChanged()
            true
        }
        else -> false
    }
}