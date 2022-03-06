package com.aranegav.fizzbuzz.presentation.result.list

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.aranegav.fizzbuzz.databinding.ResultLayoutBinding

class ResultView(context: Context, attributeSet: AttributeSet? = null) :
    ConstraintLayout(context, attributeSet) {

    private val binding = ResultLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var result: String? = null
        set(value) {
            field = value
            binding.resultTextview.text = value
        }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT)
    }
}