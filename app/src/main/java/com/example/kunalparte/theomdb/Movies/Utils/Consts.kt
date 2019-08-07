package com.example.kunalparte.theomdb.Movies.Utils

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.support.v4.content.ContextCompat.getSystemService
import android.net.ConnectivityManager



class Consts {

    companion object{
        val BASE_URL = "http://www.omdbapi.com/"
        val API_KEY = "3cb55802"
        val EXTRA_MOVIE_DETAIL = "moviewDetails"

        fun isNetworkConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

            return cm!!.activeNetworkInfo != null
        }
    }
}