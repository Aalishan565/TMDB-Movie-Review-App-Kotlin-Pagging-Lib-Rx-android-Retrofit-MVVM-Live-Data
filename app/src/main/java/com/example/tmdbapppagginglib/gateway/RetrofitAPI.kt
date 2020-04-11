package com.example.tmdbapppagginglib.gateway

import com.example.tmdbapppagginglib.model.MoviesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("movie/popular?")
    fun getPopularMoviesResponse(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Long
    ): Observable<MoviesResponse>?
}
