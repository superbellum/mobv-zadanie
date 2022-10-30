package com.example.mobvzadanie.fragments.findmypub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mobvzadanie.R
import com.example.mobvzadanie.databinding.FragmentFindPubFormBinding

class FindPubFormFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentFindPubFormBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_find_pub_form, container, false
        )

        binding.applyFormButton.setOnClickListener {
            it.findNavController().navigate(
                FindPubFormFragmentDirections.actionFormFragmentToShowFragment(
                    binding.formNameEdit.text.toString(),
                    binding.formPubNameEdit.text.toString(),
                    binding.formPubLatitudeEdit.text.toString(),
                    binding.formPubLongitudeEdit.text.toString(),
                )
            )
        }
        return binding.root
    }
}