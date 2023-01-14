package com.kevin.uasmovieapp.services

import com.kevin.uasmovieapp.models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=1c968e6f2f205a5b0b64b2ce39e12fd0")
    fun getMovieList(): Call<MovieResponse>
}