package com.aranegav.fizzbuzz.presentation.result.list

import androidx.recyclerview.widget.RecyclerView

class ResultRecyclerViewHolder(private val resultView: ResultView) :
    RecyclerView.ViewHolder(resultView) {
        fun bind(result: String) {
            resultView.result = result
        }
}