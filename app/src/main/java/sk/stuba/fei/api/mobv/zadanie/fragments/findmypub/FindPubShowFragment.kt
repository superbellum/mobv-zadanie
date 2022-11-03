package sk.stuba.fei.api.mobv.zadanie.fragments.findmypub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentFindPubMapBinding
import sk.stuba.fei.api.mobv.zadanie.service.IntentService

class FindPubShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentFindPubMapBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_find_pub_map, container, false
        )

        val args = FindPubShowFragmentArgs.fromBundle(requireArguments())
        val findMyPubFormData = args.findMyPubFormData

        binding.nameText.text = findMyPubFormData.name
        binding.pubNameText.text = findMyPubFormData.pubName

        binding.showMapButton.setOnClickListener {
            IntentService.showPubOnMap(
                fragment = this,
                latitude = findMyPubFormData.latitude,
                longitude = findMyPubFormData.longitude
            )
        }

        return binding.root
    }
}