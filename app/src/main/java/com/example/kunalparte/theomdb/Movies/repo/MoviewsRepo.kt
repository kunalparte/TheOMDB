package com.example.kunalparte.theomdb.Movies.repo

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.kunalparte.theomdb.Movies.model.Movie
import com.example.kunalparte.theomdb.Movies.model.MoviesList
import com.example.kunalparte.theomdb.Movies.repo.MoviesDao.OnApiCallFinished
import kotlinx.coroutines.*
import java.net.Proxy

class MoviewsRepo () : OnApiCallFinished{

    var moviesList: MutableLiveData<List<Movie>> = MutableLiveData()
    var movie : MutableLiveData<Movie> = MutableLiveData()
    val movieDao : MoviesDaoImpl = MoviesDaoImpl(this)

    //makes a call to MoviesList Api
    fun getMoviesList( searchEvent : String,  page : Int):LiveData<List<Movie>>{
        CoroutineScope(Dispatchers.Main).launch {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                movieDao.getMoviesList(searchEvent, page)
            }
        }
        return moviesList
    }

    //Makes a call to single Movie Api
    fun getMovie(id : String) :LiveData<Movie>{
        CoroutineScope(Dispatchers.Main).launch {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                movieDao.getMovie(id)
            }
        }
        return movie
    }

    //fetches Movies on pagination
    fun fetchMoviesFromApi(searchEvent : String,  page : Int){
        movieDao.getMoviesList(searchEvent, page)

    }


    // Reciees Api Response
    //checks what type is Api response and behaves accordingly
    override fun onApiCallResponse(response: Any) {
        if (response is Movie){
            movie.postValue(response)
        }else{
            var movieListObj = response as MoviesList
            moviesList.postValue(movieListObj.Search)

        }
    }

    //Recives error for Api request failed
    override fun onApicallFailed(t: Throwable) {

    }
}