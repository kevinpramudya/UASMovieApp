package com.kevin.uasmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kevin.uasmovieapp.models.Movie
import com.kevin.uasmovieapp.models.Series
import kotlinx.android.synthetic.main.activity_detail_movie.*
import kotlinx.android.synthetic.main.activity_detail_movie.img_item_photo
import kotlinx.android.synthetic.main.activity_detail_series.*

class DetailSeriesActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_Series = "extra_series"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_series)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        img_item_photo.clipToOutline = true

        val detailSeries = intent.getParcelableExtra<Series>(DetailSeriesActivity.EXTRA_Series)

        if (detailSeries!=null){
            val IMAGE_BASE = "https://image.tmdb.org/t/p/w500"
            Glide.with(this).load(IMAGE_BASE + detailSeries.poster).into(img_item_photo)
            series_item_name.text = detailSeries.title
            series_item_description.text = detailSeries.overview
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}