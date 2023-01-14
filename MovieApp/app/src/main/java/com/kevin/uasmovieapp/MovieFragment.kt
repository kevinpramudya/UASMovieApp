package com.kevin.uasmovieapp


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kevin.uasmovieapp.models.Movie
import com.kevin.uasmovieapp.models.MovieResponse
import com.kevin.uasmovieapp.services.MovieApiInterface
import com.kevin.uasmovieapp.services.MovieApiService
import kotlinx.android.synthetic.main.activity_movie_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class MovieFragment : Fragment() {
    private val movies = arrayListOf<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_movie_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_movies_list.layoutManager = LinearLayoutManager(this.context)
        rv_movies_list.setHasFixedSize(true)
        getMovieData{
            movies: List<Movie> ->
            rv_movies_list.adapter = MovieAdapter(movies)
        }
        showRecyclerView()
    }



    private fun getMovieData(callback: (List<Movie>) -> Unit) {
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object :Callback<MovieResponse>{
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                return callback(response.body()!!.movies)
            }
        })
    }

    private fun showRecyclerView() {
        rv_movies_list.layoutManager = LinearLayoutManager(this.context)
        rv_movies_list.adapter = MovieAdapter(movies)
    }
}