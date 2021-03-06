package com.aranegav.fizzbuzz.presentation.input.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.addTextChangedListener
import com.aranegav.fizzbuzz.R
import com.aranegav.fizzbuzz.databinding.ValueInputLayoutBinding

enum class InputType {
    TEXT,
    NUMBER
}

/**
 * Custom View used to let the user type a value of a given type (Text or Number) and return the input as a String
 */

class ValueInputView(context: Context, attributeSet: AttributeSet? = null) :
    ConstraintLayout(context, attributeSet) {

    private val binding = ValueInputLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var header: String? = null
        set(value) {
            field = value
            binding.headerTextview.text = value
        }

    var inputType: InputType = InputType.TEXT
        set(value) {
            field = value
            //Update our EditText's settings following the input type we want to select
            binding.inputEdittext.text.clear()
            binding.inputEdittext.inputType = when (value) {
                InputType.TEXT -> android.text.InputType.TYPE_CLASS_TEXT
                InputType.NUMBER -> android.text.InputType.TYPE_CLASS_NUMBER
            }
        }

    var stateText: String? = null
        set(value) {
            field = value
            binding.statusTextview.text = value
        }

    var onInputChange: ((input: String) -> Unit)? = null

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)

        //Retrieve attributes and apply
        attributeSet?.let { attributesSafe ->
            val attributes =
                context.obtainStyledAttributes(attributesSafe, R.styleable.ValueInputView)
            attributes.getString(R.styleable.ValueInputView_header)?.let {
                header = it
            }
            attributes.getInteger(R.styleable.ValueInputView_inputType, 0).let {
                inputType = InputType.values()[it]
            }
            attributes.recycle()
        }

        //When text change, notify through the onInputChange listener
        binding.inputEdittext.addTextChangedListener { updatedText ->
            onInputChange?.invoke(updatedText.toString())
        }
    }
}