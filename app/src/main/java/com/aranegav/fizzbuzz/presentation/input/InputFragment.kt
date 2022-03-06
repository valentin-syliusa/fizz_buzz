package com.aranegav.fizzbuzz.presentation.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aranegav.fizzbuzz.R
import com.aranegav.fizzbuzz.databinding.InputFragmentLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputFragment : Fragment() {

    private var _binding: InputFragmentLayoutBinding? = null

    private val binding: InputFragmentLayoutBinding
        get() = _binding!!

    private val viewModel: InputViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = InputFragmentLayoutBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observableState.observe(viewLifecycleOwner) {
            it?.let { state ->
                when (state) {
                    is State.InvalidInput -> {
                        //Update state text for each field if their value is valid or invalid
                        binding.int1Valueinputview.stateText =
                            if (state.isInt1Valid) getString(R.string.valid_input) else getString(R.string.invalid_input)
                        binding.int2Valueinputview.stateText =
                            if (state.isInt2Valid) getString(R.string.valid_input) else getString(R.string.invalid_input)
                        binding.limitValueinputview.stateText =
                            if (state.isLimitValid) getString(R.string.valid_input) else getString(R.string.invalid_input)
                        binding.str1Valueinputview.stateText =
                            if (state.isStr1Valid) getString(R.string.valid_input) else getString(R.string.invalid_input)
                        binding.str2Valueinputview.stateText =
                            if (state.isStr2Valid) getString(R.string.valid_input) else getString(R.string.invalid_input)
                        //Button is disabled until all fields are valid
                        binding.validateButton.isEnabled = false
                    }
                    is State.ValidInput -> {
                        //Update state text for all fields to notify that their value is valid
                        binding.int1Valueinputview.stateText = getString(R.string.valid_input)
                        binding.int2Valueinputview.stateText = getString(R.string.valid_input)
                        binding.limitValueinputview.stateText = getString(R.string.valid_input)
                        binding.str1Valueinputview.stateText = getString(R.string.valid_input)
                        binding.str2Valueinputview.stateText = getString(R.string.valid_input)
                        //Button is enabled when all fields are valid
                        binding.validateButton.isEnabled = true
                    }
                }
            }
        }

        //Set onInputChange listeners for each fields
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
            //When button is clicked, we retrieve our latest state, that must be "ValidInput" to pass InputData to next Fragment
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
        //Reload data each time Fragment is resumed
        viewModel.loadData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}