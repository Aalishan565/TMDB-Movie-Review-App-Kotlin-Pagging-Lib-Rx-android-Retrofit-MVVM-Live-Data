package com.example.tmdbapppagginglib.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.tmdbapppagginglib.model.MovieDataSourceFactory
import com.example.tmdbapppagginglib.model.Result

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    var itemPagedList: LiveData<PagedList<Result>>
    var liveDataSource: LiveData<PageKeyedDataSource<Long, Result>>

    private val itemDataSourceFactory: MovieDataSourceFactory =
        MovieDataSourceFactory(application)

    init {
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(50)
            .build()
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

    fun refresh() {
        itemDataSourceFactory.getItemLiveDataSource().value?.invalidate()
    }
}