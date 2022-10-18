package com.example.android.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.android.movieapp.R
import com.example.android.movieapp.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false)

        //getting the bundle
        val movie = args.movie

        binding.title.text = movie.name
        binding.cover.load(movie.image.original) {
            crossfade(true)
            crossfade(1000)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}