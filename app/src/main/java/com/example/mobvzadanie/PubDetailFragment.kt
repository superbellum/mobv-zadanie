package com.example.mobvzadanie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mobvzadanie.data.Datasource
import com.example.mobvzadanie.databinding.FragmentPubDetailBinding
import com.example.mobvzadanie.helpers.IntentHelper

class PubDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentPubDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_pub_detail, container, false
        )

        val args = PubDetailFragmentArgs.fromBundle(requireArguments())
        binding.pub = args.pub

        binding.openPubMapButton.setOnClickListener {
            IntentHelper.showPubOnMap(
                fragment = this,
                latitude = args.pub.lat.toString(),
                longitude = args.pub.lon.toString()
            )
        }

        binding.deletePubButton.setOnClickListener { view ->
            Datasource.pubs.remove(binding.pub)
            view.findNavController().navigateUp()
        }

        return binding.root
    }
}