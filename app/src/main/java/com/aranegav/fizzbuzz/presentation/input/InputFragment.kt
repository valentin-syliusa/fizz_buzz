package com.aranegav.fizzbuzz.presentation.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aranegav.fizzbuzz.databinding.InputLayoutBinding

class InputFragment : Fragment() {

    private var _binding: InputLayoutBinding? = null

    private val binding: InputLayoutBinding
        get() = _binding!!

    private val viewModel: InputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = InputLayoutBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observableState.observe(viewLifecycleOwner) {
            it?.let { state ->
                when (state) {
                    is State.InvalidInput -> {
                        binding.int1Valueinputview.stateText =
                            if (state.isInt1Valid) "Donnée valide" else "Donnée invalide"
                        binding.int2Valueinputview.stateText =
                            if (state.isInt2Valid) "Donnée valide" else "Donnée invalide"
                        binding.limitValueinputview.stateText =
                            if (state.isLimitValid) "Donnée valide" else "Donnée invalide"
                        binding.str1Valueinputview.stateText =
                            if (state.isStr1Valid) "Donnée valide" else "Donnée invalide"
                        binding.str2Valueinputview.stateText =
                            if (state.isStr2Valid) "Donnée valide" else "Donnée invalide"
                        binding.validateButton.isEnabled = false
                    }
                    is State.ValidInput -> {
                        binding.validateButton.isEnabled = true
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}