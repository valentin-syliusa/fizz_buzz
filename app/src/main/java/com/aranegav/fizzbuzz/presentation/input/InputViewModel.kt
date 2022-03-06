package com.aranegav.fizzbuzz.presentation.input

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class InputData(
    val int1: Int? = null,
    val int2: Int? = null,
    val limit: Int? = null,
    val str1: String? = null,
    val str2: String? = null,
)

sealed class State {
    data class InvalidInput(
        val isInt1Valid: Boolean,
        val isInt2Valid: Boolean,
        val isLimitValid: Boolean,
        val isStr1Valid: Boolean,
        val isStr2Valid: Boolean,
    ) : State()

    data class ValidInput(
        val int1: Int,
        val int2: Int,
        val limit: Int,
        val str1: String,
        val str2: String,
    ) : State()
}

@HiltViewModel
class InputViewModel @Inject constructor() : ViewModel() {

    private val state = MutableLiveData<State>()

    val observableState: LiveData<State> = state

    private var inputData = InputData()

    fun loadData() {
        updateState()
    }

    fun updateInt1Input(inputAsString: String) {
        inputData = inputData.copy(
            int1 = inputAsString.toIntOrNull()
        )
        updateState()
    }

    fun updateInt2Input(inputAsString: String) {
        inputData = inputData.copy(
            int2 = inputAsString.toIntOrNull()
        )
        updateState()
    }

    fun updateLimitInput(inputAsString: String) {
        inputData = inputData.copy(
            limit = inputAsString.toIntOrNull()
        )
        updateState()
    }

    fun updateStr1Input(inputAsString: String) {
        inputData = inputData.copy(
            str1 = inputAsString
        )
        updateState()
    }

    fun updateStr2Input(inputAsString: String) {
        inputData = inputData.copy(
            str2 = inputAsString
        )
        updateState()
    }

    private fun updateState() {
        val inputData = inputData
        state.value = if (inputData.int1 != null &&
            inputData.int2 != null &&
            inputData.limit != null &&
            !inputData.str1.isNullOrEmpty() &&
            !inputData.str2.isNullOrEmpty()
        ) {
            State.ValidInput(inputData.int1,
                inputData.int2,
                inputData.limit,
                inputData.str1,
                inputData.str2)
        } else {
            State.InvalidInput(
                isInt1Valid = inputData.int1 != null,
                isInt2Valid = inputData.int2 != null,
                isLimitValid = inputData.limit != null,
                isStr1Valid = !inputData.str1.isNullOrEmpty(),
                isStr2Valid = !inputData.str2.isNullOrEmpty()
            )
        }
    }
}