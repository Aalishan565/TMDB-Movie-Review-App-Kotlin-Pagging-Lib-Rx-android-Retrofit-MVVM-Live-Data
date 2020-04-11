package com.example.tmdbapppagginglib.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.tmdbapppagginglib.R
import com.example.tmdbapppagginglib.view.adapter.MovieAdapter
import com.example.tmdbapppagginglib.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnRefreshListener {

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showProgressBar()
        adapter = MovieAdapter(this)
        rvMovies.layoutManager = GridLayoutManager(this, 2)

        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieViewModel.itemPagedList.observe(this, Observer { t ->
            adapter.submitList(t)
            hideProgressBar()
            swipeRefreshLayout.isRefreshing = false
        })
        rvMovies.adapter = adapter
        swipeRefreshLayout.setOnRefreshListener(this)


    }

    override fun onRefresh() {
        movieViewModel.refresh()
    }

    private fun hideProgressBar() {
        if (null != progressBar && progressBar.isVisible) {
            progressBar.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
}
