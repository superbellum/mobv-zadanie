package sk.stuba.fei.api.mobv.zadanie.fragments.findmypub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentFindPubMapBinding
import sk.stuba.fei.api.mobv.zadanie.helpers.IntentHelper

class FindPubShowFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentFindPubMapBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_find_pub_map, container, false
        )

        val args = sk.stuba.fei.api.mobv.zadanie.fragments.findmypub.FindPubShowFragmentArgs.fromBundle(
            requireArguments()
        )
        binding.nameText.text = args.name
        binding.pubNameText.text = args.pubName

        binding.showMapButton.setOnClickListener {
            IntentHelper.showPubOnMap(
                this,
                args.pubLatitude,
                args.pubLongitude
            )
        }

        return binding.root
    }
}