package com.example.mobvzadanie.fragments.listpubs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.mobvzadanie.R
import com.example.mobvzadanie.adapter.PubItemAdapter
import com.example.mobvzadanie.data.Datasource
import com.example.mobvzadanie.databinding.FragmentListPubsBinding

class ListPubsFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentListPubsBinding
    private var sortPubsByNameAsc = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_list_pubs, container, false
        )

        // recycler view
        binding.establishtmentsTotalText.text = "Total: ${Datasource.pubs.size}"
        val recyclerView = binding.recyclerView
        recyclerView.adapter = PubItemAdapter(requireContext(), Datasource.pubs)
        recyclerView.setHasFixedSize(true)

        // menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.sort_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.sort_pub_by_name_menu_item) {
            Datasource.pubs.apply {
                if (sortPubsByNameAsc) {
                    sortBy { it.tags?.name }
                } else {
                    sortByDescending { it.tags?.name }
                }
            }
            sortPubsByNameAsc = !sortPubsByNameAsc
            binding.recyclerView.adapter?.notifyDataSetChanged()
            return true
        }

        return false
    }
}