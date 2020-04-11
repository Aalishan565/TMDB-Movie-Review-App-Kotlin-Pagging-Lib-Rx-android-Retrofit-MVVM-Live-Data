package com.example.tmdbapppagginglib.model

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource

class MovieDataSourceFactory(private val context: Context) :
    DataSource.Factory<Long, Result>() {

    private val itemLiveDataSource: MutableLiveData<PageKeyedDataSource<Long, Result>> =
        MutableLiveData()

    override fun create(): DataSource<Long, Result> {
        val itemDataSource =
            MovieDataSource(context)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Long, Result>> {
        return itemLiveDataSource
    }
}