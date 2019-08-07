package com.example.kunalparte.theomdb.Movies.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.view.SearchEvent
import com.example.kunalparte.theomdb.Movies.model.Movie

class MoviesViewModel : ViewModel() {

    val moviewsRepo : MoviewsRepo by lazy {
        MoviewsRepo()
    }

    fun getMoviesList(searchEvent: String, page: Int) : LiveData<List<Movie>>{
        return moviewsRepo.getMoviesList(searchEvent, page)
    }

    fun getMovie(id : String) : LiveData<Movie>{
        return moviewsRepo.getMovie(id)
    }

    fun fetchMoviesFromApi(searchEvent : String,  page : Int){
        moviewsRepo.fetchMoviesFromApi(searchEvent,page)
    }
}