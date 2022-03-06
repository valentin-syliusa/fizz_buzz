package com.aranegav.fizzbuzz.presentation.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aranegav.fizzbuzz.domain.input.model.Input
import com.aranegav.fizzbuzz.domain.input.usecase.GetResultFromInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Our View's State
 */

sealed class State {
    data class DataLoaded(val results: List<String>) : State()
}

@HiltViewModel
class ResultViewModel @Inject constructor(private val getResultFromInput: GetResultFromInput) :
    ViewModel() {

    private val state = MutableLiveData<State>()

    val observableState: LiveData<State> = state

    fun loadData(int1: Int, int2: Int, limit: Int, str1: String, str2: String) {
        //We call our use case to retrieve result from the given input and then notify through our State
        val result = getResultFromInput.perform(Input(int1, int2, limit, str1, str2))
        state.value = State.DataLoaded(result)
    }
}