package com.example.android.movieapp.ui.homepage

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movieapp.R
import com.example.android.movieapp.databinding.FragmentHomeBinding
import com.example.android.movieapp.model.MovieItem
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

        val recyclerView = binding.movieList
        binding.lifecycleOwner = this
        val layoutManager = recyclerView.layoutManager as GridLayoutManager

        //binding the adapter
        val adapter = MovieListAdapter(){onMovieClick(it)}
        recyclerView.adapter = adapter

        homeViewModel.responseMovie.observe(viewLifecycleOwner) { movieList ->
            adapter.data = movieList
        }


        //check if the last item shown in the RecyclerView is the 5th last item
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lastVisibleMovieItem = layoutManager.findLastVisibleItemPosition()
                val totalMovieItemCount = layoutManager.itemCount
                //check if the last item shown in the RecyclerView is the 5th last item
                if(lastVisibleMovieItem >= totalMovieItemCount - 5){
                    //if true then load the next page of data
                    homeViewModel.changePage()
                }
            }
        })


        return binding.root
    }

    //navigate to detail
    private fun onMovieClick(movie: MovieItem) {

        this.findNavController()
            .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie = movie))
    }
}