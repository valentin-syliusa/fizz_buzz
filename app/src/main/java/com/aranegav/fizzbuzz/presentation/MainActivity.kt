package com.aranegav.fizzbuzz.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.aranegav.fizzbuzz.databinding.MainLayoutBinding

class MainActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflate & Set Root View
        val binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}