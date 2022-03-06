package com.aranegav.fizzbuzz.presentation.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aranegav.fizzbuzz.databinding.InputLayoutBinding

class InputFragment : Fragment() {

    private var _binding: InputLayoutBinding? = null

    private val binding: InputLayoutBinding
        get() = _binding!!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}