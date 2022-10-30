package com.example.mobvzadanie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mobvzadanie.databinding.FragmentFindPubMapBinding
import com.example.mobvzadanie.helpers.IntentHelper

class FindPubMapFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentFindPubMapBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_find_pub_map, container, false
        )

        val args = FindPubMapFragmentArgs.fromBundle(requireArguments())
        binding.nameText.text = args.name
        binding.pubNameText.text = args.pubName

        binding.showMapButton.setOnClickListener {
            IntentHelper.showPubOnMap(
                this,
                args.pubLatitude,
                args.pubLongitude
            )
        }

        binding.playAnimButton.setOnClickListener {
            binding.colaAnimation.playAnimation();
        }

        return binding.root
    }
}