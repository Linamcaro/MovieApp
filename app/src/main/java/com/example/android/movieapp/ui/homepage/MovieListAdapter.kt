package com.example.android.movieapp.ui.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.android.movieapp.databinding.MovieItemListBinding
import com.example.android.movieapp.model.MovieItem

class MovieListAdapter
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMovie = data[position]
        holder.bind(currentMovie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: MovieItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //binding the movie
        fun bind(currentMovie: MovieItem) {
            binding.movieTitle.text = currentMovie.name
            binding.moviePoster.load(currentMovie.image.original) {
                crossfade(true)
                crossfade(1000)
            }

            binding.executePendingBindings()
        }

        //inflate the view
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemListBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }

    //check if an item are the same or if the content are the same
    private val movieDiffCallback = object : DiffUtil.ItemCallback<MovieItem>() {
        override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
            return newItem == oldItem
        }
    }

    //calculate the difference between two lists in a background thread
    private val differ = AsyncListDiffer(this, movieDiffCallback)
    var data: List<MovieItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }
}


