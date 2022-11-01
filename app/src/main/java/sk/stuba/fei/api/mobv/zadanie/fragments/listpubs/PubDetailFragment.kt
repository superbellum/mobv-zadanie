package sk.stuba.fei.api.mobv.zadanie.fragments.listpubs

import android.app.AlertDialog
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
import androidx.navigation.fragment.findNavController
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.Datasource
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentPubDetailBinding
import sk.stuba.fei.api.mobv.zadanie.helpers.IntentHelper

class PubDetailFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentPubDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pub_detail, container, false
        )

        val args = PubDetailFragmentArgs.fromBundle(requireArguments())
        binding.pub = args.pub

        // menu
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.pub_detail_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem) = when (menuItem.itemId) {
        R.id.open_pub_on_map_menu_item -> {
            IntentHelper.showPubOnMap(
                fragment = this,
                latitude = binding.pub!!.lat.toString(),
                longitude = binding.pub!!.lon.toString()
            )
            true
        }
        R.id.delete_pub_menu_item -> {
            showDeletePubAlertDialog()
            true
        }
        else -> false
    }

    private fun showDeletePubAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Pub")
            .setMessage("Are you sure you want to delete this pub?")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                Datasource.pubs.remove(binding.pub)
                findNavController().navigateUp()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }
}