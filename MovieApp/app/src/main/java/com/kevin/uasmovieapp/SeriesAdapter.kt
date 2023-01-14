package com.kevin.uasmovieapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kevin.uasmovieapp.models.Series
import kotlinx.android.synthetic.main.series_item.view.*

class SeriesAdapter(
    private val series : List<Series>
) : RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>() {

    class SeriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        fun bindSeries(series: Series) {
            itemView.series_title.text = series.title
            itemView.series_overview.text = series.overview
            Glide.with(itemView).load(IMAGE_BASE + series.poster).into(itemView.series_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesAdapter.SeriesViewHolder {
        return SeriesAdapter.SeriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.series_item, parent, false)
        )
    }

    override fun getItemCount(): Int = series.size

    override fun onBindViewHolder(holder: SeriesAdapter.SeriesViewHolder, position: Int) {
        val serie = series[position]
        holder.bindSeries(series.get(position))

        holder.itemView.setOnClickListener {
            moveToSeriesDetail(serie,it)
        }
    }

    private fun moveToSeriesDetail(series: Series, it: View) {
        val detailSeriesIntent = Intent(it.context, DetailSeriesActivity::class.java)
        detailSeriesIntent.putExtra(DetailSeriesActivity.EXTRA_Series,series)
        it.context.startActivity(detailSeriesIntent)
    }
}