package com.example.kunalparte.theomdb.Movies.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.kunalparte.theomdb.Movies.Utils.Consts
import com.example.kunalparte.theomdb.Movies.Utils.interfaces.OnRecyclerViewItemClicked
import com.example.kunalparte.theomdb.Movies.adapter.MoviesListAdapter
import com.example.kunalparte.theomdb.Movies.repo.MoviesViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import com.example.kunalparte.theomdb.R
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.kunalparte.theomdb.Movies.model.Movie
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity(), OnRecyclerViewItemClicked {


    val moviesListAdapter : MoviesListAdapter by lazy {
        MoviesListAdapter(this,this )
    }
    lateinit var moviesViewModel: MoviesViewModel
    private var loading = true
    var pastVisiblesItems: Int = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0
    var pageNo : Int = 1
    var moviesListTemp : List<Movie> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpActionBar()
        if (Consts.isNetworkConnected(this)) {
            moviesViewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
            setRecyclerView()
            setDataOnRecyclerView()
            onRecyclerViewScrolled()
            setSearchEditText()
        }else{
            Toast.makeText(this,"Please Connect to Internet", Toast.LENGTH_LONG).show()

        }
    }

    fun setUpActionBar(){
        toolbar.setTitle("Movie")
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun setRecyclerView() {
        //sets recyclerView with adapter and layoutmanager
        movies_recycler_view.let {
            it.layoutManager = LinearLayoutManager(this)
            it.adapter = moviesListAdapter
        }
    }

    fun setDataOnRecyclerView(){
        //fetches data from api with a search string that is passed
        //makes a call to viemodel method which returns Livedata of List
        // which is Observed by the observer for the data changes.
        moviesViewModel.getMoviesList("movies",pageNo).observe(this, Observer {
            if(it != null && it.size > 0) {
                moviesListTemp = it
                moviesListAdapter.moviesList.addAll(moviesListTemp)
                progress_bar_layout.visibility = View.GONE
                moviesListAdapter.notifyDataSetChanged()
                loading = true
            }
            Log.e("resp", Gson().toJson(it))
        })
    }

    override fun onItemClickListener(position: Int) {
        //open MovieDetailActivity to display Movie Details
        var intent : Intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra(Consts.EXTRA_MOVIE_DETAIL,moviesListAdapter.moviesList.get(position).imdbID)
        startActivity(intent)
    }

    fun onRecyclerViewScrolled(){
        // On RecyclerScrolled for pagination
        movies_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                var linearLayoutManager : LinearLayoutManager = movies_recycler_view.layoutManager as LinearLayoutManager
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.getChildCount()
                    totalItemCount = linearLayoutManager.getItemCount()
                    pastVisiblesItems = linearLayoutManager.findFirstCompletelyVisibleItemPosition()

                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false

                            //increments the page no with search string passed to fetch data for incremented pageNo
                            pageNo++
                            moviesViewModel.fetchMoviesFromApi("movies", pageNo)
                            progress_bar_layout.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })
    }

    fun setSearchEditText(){
        activity_movies_list_search_et.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //threshold set to 3
                if (s!!.length > 3){
                    moviesViewModel.fetchMoviesFromApi(s.toString(), pageNo)
                    moviesListAdapter.moviesList= ArrayList()
                }else{
                    pageNo = 1
                    moviesViewModel.fetchMoviesFromApi("movies", pageNo)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
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
