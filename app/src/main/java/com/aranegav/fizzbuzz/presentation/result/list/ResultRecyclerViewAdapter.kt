package com.aranegav.fizzbuzz.presentation.result.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ResultRecyclerViewAdapter : RecyclerView.Adapter<ResultRecyclerViewHolder>() {

    var results: List<String> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultRecyclerViewHolder =
        ResultRecyclerViewHolder(
            resultView = ResultView(parent.context)
        )

    override fun onBindViewHolder(holder: ResultRecyclerViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount(): Int = results.size
}