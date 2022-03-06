package com.aranegav.fizzbuzz.presentation.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

        binding.int1Valueinputview.onInputChange = { updatedInt1Value ->
            viewModel.updateInt1Input(updatedInt1Value)
        }
        binding.int2Valueinputview.onInputChange = { updatedInt2Value ->
            viewModel.updateInt2Input(updatedInt2Value)
        }
        binding.limitValueinputview.onInputChange = { updatedLimitValue ->
            viewModel.updateLimitInput(updatedLimitValue)
        }
        binding.str1Valueinputview.onInputChange = { updatedStr1Value ->
            viewModel.updateStr1Input(updatedStr1Value)
        }
        binding.str2Valueinputview.onInputChange = { updatedStr2Value ->
            viewModel.updateStr2Input(updatedStr2Value)
        }

        binding.validateButton.setOnClickListener {
            (viewModel.observableState.value as? State.ValidInput)?.let { inputData ->
                findNavController().navigate(InputFragmentDirections.presentResults(
                    int1 = inputData.int1,
                    int2 = inputData.int2,
                    limit = inputData.limit,
                    str1 = inputData.str1,
                    str2 = inputData.str2
                ))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}