package com.company.home.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.home.R
import com.company.home.databinding.FragmentHomeBinding
import com.company.home.di.loadHomeModule
import com.company.home.ui.adapters.HomeViewPagerAdapter
import com.company.home.ui.adapters.PopularShowsRecyclerviewAdapter
import com.company.home.ui.adapters.TopRatedShowsRecyclerviewAdapter
import com.vickikbt.notflix.util.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(R.layout.fragment_home), StateListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()
    private fun injectFeatures() = loadHomeModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        injectFeatures()
        viewModel.stateListener = this

        initUI()

    }


    private fun initUI() {
        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, { nowPlayingMovies ->
            binding.viewPagerTrendingShows.adapter = HomeViewPagerAdapter(requireActivity(), nowPlayingMovies){movie->
                val action = HomeFragmentDirections.homeToDetails(movieId = movie.id!!, cacheId = movie.cacheId!!)
                findNavController().navigate(action)
            }
            binding.dotsTrendingShows.setViewPager(binding.viewPagerTrendingShows)
        })

        viewModel.trendingMovies.observe(viewLifecycleOwner, { trendingMovies ->
            binding.recyclerviewPopularMovies.adapter = PopularShowsRecyclerviewAdapter(trendingMovies){movie->
                val action = HomeFragmentDirections.homeToDetails(movieId = movie.id!!, cacheId = movie.cacheId!!)
                findNavController().navigate(action)
            }
        })

        viewModel.popularMovies.observe(viewLifecycleOwner, { popularMovies ->
            binding.recyclerviewTopRatedMovies.adapter = TopRatedShowsRecyclerviewAdapter(popularMovies){movie->
                val action = HomeFragmentDirections.homeToDetails(movieId = movie.id!!, cacheId = movie.cacheId!!)
                findNavController().navigate(action)
            }
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, { upcomingMovies ->
            binding.recyclerviewPopularTvShows.adapter = PopularShowsRecyclerviewAdapter(upcomingMovies){movie->
                val action = HomeFragmentDirections.homeToDetails(movieId = movie.id!!, cacheId = movie.cacheId!!)
                findNavController().navigate(action)
            }
        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onLoading() {
        requireActivity().log("Loading...")
        //binding.shimmerHome.show()
    }

    override fun onSuccess(message: String) {
        if (isAdded) requireActivity().log(message)
        //binding.shimmerHome.hide()
    }

    override fun onError(message: String?) {
        if (isAdded) {
            requireActivity().toast(message!!)
            requireActivity().log(message)
        }
        //binding.shimmerHome.show()
    }

}