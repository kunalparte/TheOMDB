package com.example.kunalparte.sundaymobtask.utils

import com.example.kunalparte.theomdb.Movies.Utils.Consts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object{
        var retrofit : Retrofit? = null

        fun getRetrofitClient(): Retrofit? {
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(Consts.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}