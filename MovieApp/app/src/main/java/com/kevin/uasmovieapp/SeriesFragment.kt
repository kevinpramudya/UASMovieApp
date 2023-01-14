package com.kevin.uasmovieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.uasmovieapp.models.Movie
import com.kevin.uasmovieapp.models.MovieResponse
import com.kevin.uasmovieapp.models.Series
import com.kevin.uasmovieapp.models.SeriesResponse
import com.kevin.uasmovieapp.services.MovieApiInterface
import com.kevin.uasmovieapp.services.MovieApiService
import com.kevin.uasmovieapp.services.SeriesApiInterface
import com.kevin.uasmovieapp.services.SeriesApiService
import kotlinx.android.synthetic.main.activity_movie_fragment.*
import kotlinx.android.synthetic.main.activity_series_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SeriesFragment : Fragment() {
    private val series = arrayListOf<Series>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_series_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_series_list.layoutManager = LinearLayoutManager(this.context)
        rv_series_list.setHasFixedSize(true)
        getSeriesData{
                series: List<Series> ->
            rv_series_list.adapter = SeriesAdapter(series)
        }
        showRecyclerView()
    }

    private fun getSeriesData(callback: (List<Series>) -> Unit) {
        val apiService = SeriesApiService.getInstance().create(SeriesApiInterface::class.java)
        apiService.getSeriesList().enqueue(object : Callback<SeriesResponse> {
            override fun onFailure(call: Call<SeriesResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<SeriesResponse>, response: Response<SeriesResponse>) {
                return callback(response.body()!!.series)
            }
        })
    }


    private fun showRecyclerView() {
        rv_series_list.layoutManager = LinearLayoutManager(this.context)
        rv_series_list.adapter = SeriesAdapter(series)
    }
}