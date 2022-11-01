package sk.stuba.fei.api.mobv.zadanie.fragments.findmypub

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import sk.stuba.fei.api.mobv.zadanie.R
import sk.stuba.fei.api.mobv.zadanie.data.FindMyPubFormData
import sk.stuba.fei.api.mobv.zadanie.databinding.FragmentFindPubFormBinding

class FindPubFormFragment : Fragment() {
    private lateinit var binding: FragmentFindPubFormBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_find_pub_form, container, false
        )

        binding.applyFormButton.setOnClickListener {
            if (!checkFormHasErrors()) {
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
        }
        return binding.root
    }

    private fun checkFormHasErrors(): Boolean {
        var hasError = false

        if (isFormEditEmpty(binding.formNameEdit)) {
            binding.formNameEdit.error = "Must not be empty!"
            hasError = true
        }

        if (isFormEditEmpty(binding.formPubNameEdit)) {
            binding.formPubNameEdit.error = "Must not be empty!"
            hasError = true
        }

        if (isFormEditEmpty(binding.formPubLatitudeEdit)) {
            binding.formPubLatitudeEdit.error = "Must not be empty!"
            hasError = true
        }

        if (isFormEditEmpty(binding.formPubLongitudeEdit)) {
            binding.formPubLongitudeEdit.error = "Must not be empty!"
            hasError = true
        }

        return hasError
    }

    private fun isFormEditEmpty(editText: EditText) = isEmpty(editText.text.toString().trim())
}