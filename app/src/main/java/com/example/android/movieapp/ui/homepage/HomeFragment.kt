package com.example.android.movieapp.ui.homepage

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.movieapp.R
import com.example.android.movieapp.databinding.FragmentHomeBinding
import com.example.android.movieapp.model.MovieItem
import com.example.android.movieapp.ui.login.LoginFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home,container,false)

        //ViewModel
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.homeViewModel = homeViewModel

        binding.lifecycleOwner = this

        //adapter
        val adapter = MovieListAdapter(){onMovieClick(it)}
        binding.movieList.adapter = adapter

        homeViewModel.responseMovie.observe(viewLifecycleOwner) { movieList ->
            adapter.data = movieList
        }


        return binding.root
    }

    //navigate to detail
    private fun onMovieClick(movie: MovieItem) {

        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie = movie))
    }
}