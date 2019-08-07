package com.example.kunalparte.theomdb.Movies.repo

import android.util.Log
import com.example.kunalparte.sundaymobtask.utils.RetrofitClient
import com.example.kunalparte.theomdb.Movies.Utils.Consts
import com.example.kunalparte.theomdb.Movies.api.ApiClient
import com.example.kunalparte.theomdb.Movies.model.Movie
import com.example.kunalparte.theomdb.Movies.model.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesDaoImpl(onApiCallFinished: MoviesDao.OnApiCallFinished):MoviesDao {
    var onApiCallFinishedObj = onApiCallFinished

    override fun getMoviesList(searchStr: String, pageNo: Int) {
        var moviesList : List<Movie> = emptyList()
        val movisListCall = RetrofitClient.getRetrofitClient()!!.create(ApiClient::class.java)
                movisListCall.getMoviesList(searchStr, Consts.API_KEY, pageNo).enqueue(object : Callback<MoviesList> {
                    override fun onFailure(call: Call<MoviesList>, t: Throwable) {
                        Log.e("err", t.message)
                    }

                    override fun onResponse(call: Call<MoviesList>, response: Response<MoviesList>) {
                        if (response.isSuccessful){
                            response.body()?.let { onApiCallFinishedObj.onApiCallResponse(it) }
                        }
                    }

                })
    }

    override fun getMovie(id: String) {
        var movieCall = RetrofitClient.getRetrofitClient()!!.create(ApiClient::class.java).getMovies(id, Consts.API_KEY)
        movieCall.enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e("err", t.message)
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                response.body()?.let { onApiCallFinishedObj.onApiCallResponse(it) }
            }
        })
    }
}