package com.example.kunalparte.theomdb.Movies.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.transition.Visibility
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kunalparte.sundaymobtask.utils.GlideLoader
import com.example.kunalparte.theomdb.Movies.Utils.Consts
import com.example.kunalparte.theomdb.Movies.repo.MoviesViewModel
import com.example.kunalparte.theomdb.R
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class MovieDetailActivity : AppCompatActivity() {

    lateinit var moviesViewModel: MoviesViewModel
    var movieId : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        loadIntentData()
        setUpActionBar()
        if (Consts.isNetworkConnected(this))
            setDataForMovie()
        else
            Toast.makeText(this,"Please Connect to Internet",Toast.LENGTH_LONG).show()
    }

    fun loadIntentData(){
        if (intent!= null){
            if (intent.hasExtra(Consts.EXTRA_MOVIE_DETAIL)){
                movieId = intent.getStringExtra(Consts.EXTRA_MOVIE_DETAIL)
            }
        }
    }

    fun setUpActionBar(){
        toolbar.setTitle("Movie")
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setDataForMovie(){
        moviesViewModel.getMovie(movieId).observe(this, Observer {
            if (it != null){
                var rating = it.imdbRating.toDouble() / 2
                var info : String = ""
                if (!TextUtils.isEmpty(it.Year)){
                    info = it.Year
                }else{
                    info = "Not Available" + " " + "*"
                }

                if (!TextUtils.isEmpty(it.Runtime)){
                    info = info + " " + it.Runtime + "mins" + " " + "*"
                }else{
                    info = info + " Not Available " + "*"
                }

                if (!TextUtils.isEmpty(it.Genre)){
                    info = info + " " + it.Genre + " " + "*"
                }else{
                    info = info + " Not Available " + "*"
                }

                if (!TextUtils.isEmpty(it.Language)){
                    info = info + " " + it.Language + " " + "*"
                }else{
                    info = info + " Not Available " + "*"
                }
                activity_movie_detail_img_progressbar.visibility = View.GONE
                activity_movie_detail_data_progressbar.visibility = View.GONE
                activity_movie_detail_director_tag.visibility = View.VISIBLE
                activity_movie_detail_artist_tag.visibility = View.VISIBLE

                GlideLoader.url(this).load(it.Poster).into(activity_movie_detail_iv_image)
                activity_movie_detail_name_tv.setText(it.Title)
                activity_movie_detail_rating_tv.setText("OMDB Rating - "+rating + " / " + "5")
                activity_movie_detail_info_tv.setText(info)
                activity_movie_detail_desc_tv.setText(it.Plot)
                activity_movie_detail_director_tv.setText(it.Director)
                activity_movie_detail_artist_tv.setText(it.Actors)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
