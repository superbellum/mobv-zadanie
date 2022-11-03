package sk.stuba.fei.api.mobv.zadanie.fragments.listpubs

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.IRemovePub
import sk.stuba.fei.api.mobv.zadanie.data.IRemovePub.RemoveFrom
import sk.stuba.fei.api.mobv.zadanie.data.JsonPubsViewModel
import sk.stuba.fei.api.mobv.zadanie.data.WebPubsViewModel
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentPubDetailBinding
import sk.stuba.fei.api.mobv.zadanie.service.DialogService.createAlertDialog
import sk.stuba.fei.api.mobv.zadanie.service.IntentService
import sk.stuba.fei.api.mobv.zadanie.service.NotificationService.notifyToast

class PubDetailFragment : Fragment(), MenuProvider {
    private lateinit var binding: FragmentPubDetailBinding
    private lateinit var args: PubDetailFragmentArgs
    private val webPubsViewModel: WebPubsViewModel by activityViewModels()
    private val jsonPubsViewModel: JsonPubsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pub_detail, container, false
        )

        args = PubDetailFragmentArgs.fromBundle(requireArguments())
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
            IntentService.showPubOnMap(
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
        createAlertDialog(
            context = requireContext(),
            title = "Delete Pub",
            message = "Are you sure you want to delete this pub?",
            isCancelable = true,
            onOk = { _, _ ->
                when (args.removePubFrom) {
                    RemoveFrom.JSON -> removePubFromViewModel(jsonPubsViewModel)
                    RemoveFrom.WEB -> removePubFromViewModel(webPubsViewModel)
                }
                findNavController().navigateUp()
            },
            onCancel = { dialog, _ -> dialog.cancel() }
        ).show()
    }

    private fun removePubFromViewModel(viewModel: IRemovePub) {
        viewModel.removePub(binding.pub!!).also {
            val message = if (it) {
                "Removed pub ${binding.pub!!.id}"
            } else {
                "Could not remove pub ${binding.pub!!.id}"
            }
            notifyToast(requireContext(), message, Toast.LENGTH_SHORT)
        }
    }
}