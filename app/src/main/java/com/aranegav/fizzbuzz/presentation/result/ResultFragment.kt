package com.aranegav.fizzbuzz.presentation.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranegav.fizzbuzz.databinding.ResultFragmentLayoutBinding
import com.aranegav.fizzbuzz.presentation.result.list.ResultRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment: Fragment() {

    private var _binding: ResultFragmentLayoutBinding? = null

    private val binding: ResultFragmentLayoutBinding
        get() = _binding!!

    private val viewModel: ResultViewModel by viewModels()

    private val resultRecyclerViewAdapter = ResultRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ResultFragmentLayoutBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observableState.observe(viewLifecycleOwner) {
            it?.let { state ->
                when (state) {
                    is State.DataLoaded -> {
                        resultRecyclerViewAdapter.results = state.results
                    }
                }
            }
        }

        with(binding.resultsRecyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = resultRecyclerViewAdapter
        }

        arguments?.let { argumentsSafe ->
            val inputData = ResultFragmentArgs.fromBundle(argumentsSafe)
            viewModel.loadData(
                int1 = inputData.int1,
                int2 = inputData.int2,
                limit = inputData.limit,
                str1 = inputData.str1,
                str2 = inputData.str2
            )
        } ?: run {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}