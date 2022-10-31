package sk.stuba.fei.api.mobv.zadanie.fragments.findmypub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.FindMyPubFormData
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentFindPubFormBinding

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
                FindPubFormFragmentDirections.actionFindPubFormToFindPubShow(
                    FindMyPubFormData(
                        name = binding.formNameEdit.text.toString(),
                        pubName = binding.formPubNameEdit.text.toString(),
                        latitude = binding.formPubLatitudeEdit.text.toString(),
                        longitude = binding.formPubLongitudeEdit.text.toString()
                    )
                )
            )
        }
        return binding.root
    }
}