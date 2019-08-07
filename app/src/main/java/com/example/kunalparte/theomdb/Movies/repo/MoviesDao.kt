package com.example.kunalparte.theomdb.Movies.repo

import android.support.annotation.AnyRes
import com.example.kunalparte.theomdb.Movies.model.Movie
import java.net.CacheResponse

interface MoviesDao {

    fun getMoviesList( searchStr : String,  pageNo : Int)

    fun getMovie(id : String)

    interface OnApiCallFinished{
        fun onApiCallResponse(response: Any)
        fun onApicallFailed(t : Throwable)
    }
}