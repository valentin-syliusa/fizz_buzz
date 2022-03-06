package com.aranegav.fizzbuzz.domain

import com.aranegav.fizzbuzz.domain.input.model.Input
import com.aranegav.fizzbuzz.domain.input.usecase.GetResultFromInput
import org.junit.Test

class GetResultFromInputTest {

    @Test
    fun `Check if result is valid from a given input`() {
        //Given a fake input
        val input = Input(
            int1 = 3,
            int2 = 5,
            limit = 20,
            str1 = "RATP",
            str2 = "Mappy"
        )
        val expectedResult = listOf(
            "1",
            "2",
            "RATP",
            "4",
            "Mappy",
            "RATP",
            "7",
            "8",
            "RATP",
            "Mappy",
            "11",
            "RATP",
            "13",
            "14",
            "RATPMappy",
            "16",
            "17",
            "RATP",
            "19",
            "Mappy"
        )
        //When we trigger our use case
        val getResultFromInput = GetResultFromInput()
        val result = getResultFromInput.perform(input)
        //Then, we check the validity of our result with the result we were waiting for
        assert(result == expectedResult)
    }
}