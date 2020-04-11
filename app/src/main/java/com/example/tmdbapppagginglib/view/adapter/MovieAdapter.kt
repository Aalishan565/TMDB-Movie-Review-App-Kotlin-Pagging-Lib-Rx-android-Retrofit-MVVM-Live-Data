package com.example.tmdbapppagginglib.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmdbapppagginglib.R
import com.example.tmdbapppagginglib.apputils.Constants
import com.example.tmdbapppagginglib.model.Result

class MovieAdapter(private val context: Context) :
    PagedListAdapter<Result, MovieAdapter.MovieViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var item = getItem(position)
        if (item != null) {
            Glide.with(context).load(Constants.POSTER_BASE_URL + item.poster_path)
                .into(holder.imageView)
        }
    }

    inner class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.imageView)

    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Result?> =
            object : DiffUtil.ItemCallback<Result?>() {
                override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.original_title === newItem.original_title
                }

                override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }

}