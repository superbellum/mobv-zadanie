package com.example.mobvzadanie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mobvzadanie.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )

        binding.findPubMapButton.setOnClickListener {
            it.findNavController().navigate(
                TitleFragmentDirections.actionTitleFragmentToFormFragment()
            )
        }

        binding.listPubsButton.setOnClickListener {
            it.findNavController().navigate(
                TitleFragmentDirections.actionTitleFragmentToListPubsFragment()
            )
        }

        return binding.root
    }
}