package com.example.tmdbapppagginglib.model

import android.content.Context
import androidx.paging.PageKeyedDataSource
import com.example.tmdbapppagginglib.apputils.Constants
import com.example.tmdbapppagginglib.gateway.CommunicationManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieDataSource(private val context: Context) :
    PageKeyedDataSource<Long, Result>() {

    private val FIRST_PAGE: Long = 1

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Result>
    ) {
        CommunicationManager.getPopularMoviesResponseImplMethod(
            context,
            Constants.API_KEY,
            FIRST_PAGE
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.apply {
                subscribe({ result ->
                    callback.onResult(result.results, null, FIRST_PAGE + 1)

                }, { t: Throwable? -> })
            }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Result>) {
        CommunicationManager.getPopularMoviesResponseImplMethod(
            context,
            Constants.API_KEY,
            params.key
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.apply {
                subscribe({ result ->

                    var key = if (result.total_pages >= params.key) {
                        params.key + 1
                    } else {
                        null
                    }

                    callback.onResult(result.results, key)

                }, { t: Throwable? -> })
            }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Result>) {
        CommunicationManager.getPopularMoviesResponseImplMethod(
            context,
            Constants.API_KEY,
            params.key
        )
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.apply {
                subscribe({ result ->

                    var key = if (params.key > 1) {
                        params.key - 1
                    } else {
                        null
                    }

                    callback.onResult(result.results, key)

                }, { t: Throwable? -> })
            }
    }


}