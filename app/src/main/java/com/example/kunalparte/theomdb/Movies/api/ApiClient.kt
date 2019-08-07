package com.example.kunalparte.theomdb.Movies.api

import com.example.kunalparte.theomdb.Movies.model.Movie
import com.example.kunalparte.theomdb.Movies.model.MoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET(".")
    fun getMoviesList(@Query("s") search: String,
                      @Query("apikey") apiKey : String,
                      @Query("page") pageNo : Int) : Call<MoviesList>


    @GET(".")
    fun getMovies(@Query("i") id: String,
                      @Query("apikey") apiKey : String) : Call<Movie>
}