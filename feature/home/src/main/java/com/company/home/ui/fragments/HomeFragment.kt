package com.company.home.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.company.home.R
import com.company.home.databinding.FragmentHomeBinding
import com.company.home.di.loadHomeModule
import com.company.home.ui.adapters.HomeViewPagerAdapter
import com.company.home.ui.adapters.PopularShowsRecyclerviewAdapter
import com.company.home.ui.adapters.TopRatedShowsRecyclerviewAdapter
import com.vickikbt.domain.models.Movie
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()
    private fun injectFeatures() = loadHomeModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        injectFeatures()

        initUI()

    }

    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.nowPlayingMovies.collect { uiState ->
                        when (uiState) {
                            is HomeViewModel.HomeUiState.Error -> showError(uiState.error)
                            is HomeViewModel.HomeUiState.Success -> showNowPlayingMovies(uiState.movies)
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.trendingMovies.collect { uiState ->
                        when (uiState) {
                            is HomeViewModel.HomeUiState.Error -> showError(uiState.error)
                            is HomeViewModel.HomeUiState.Success -> showTrendingMovies(uiState.movies)
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.popularMovies.collect { uiState ->
                        when (uiState) {
                            is HomeViewModel.HomeUiState.Error -> showError(uiState.error)
                            is HomeViewModel.HomeUiState.Success -> showPopularMovies(uiState.movies)
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.upcomingMovies.collect { uiState ->
                        when (uiState) {
                            is HomeViewModel.HomeUiState.Error -> showError(uiState.error)
                            is HomeViewModel.HomeUiState.Success -> showUpcomingMovies(uiState.movies)
                            else -> showLoading()
                        }
                    }
                }
            }
        }
    }

    private fun showLoading() {

    }

    private fun showError(errorMessage: String) {
        Timber.e("An error occurred: $errorMessage")
    }

    private fun showNowPlayingMovies(movies: List<Movie>) {
        binding.viewPagerTrendingShows.adapter =
            HomeViewPagerAdapter(requireActivity(), movies) { movie ->
                val action = HomeFragmentDirections.homeToDetails(
                    movieId = movie.id!!,
                    cacheId = movie.cacheId!!
                )
                findNavController().navigate(action)
            }
        binding.dotsTrendingShows.setViewPager(binding.viewPagerTrendingShows)
    }

    private fun showTrendingMovies(movies: List<Movie>) {
        binding.recyclerviewPopularMovies.adapter =
            PopularShowsRecyclerviewAdapter(movies) { movie ->
                val action = HomeFragmentDirections.homeToDetails(
                    movieId = movie.id!!,
                    cacheId = movie.cacheId!!
                )
                findNavController().navigate(action)
            }
    }

    private fun showPopularMovies(movies: List<Movie>) {
        binding.recyclerviewTopRatedMovies.adapter =
            TopRatedShowsRecyclerviewAdapter(movies) { movie ->
                val action = HomeFragmentDirections.homeToDetails(
                    movieId = movie.id!!,
                    cacheId = movie.cacheId!!
                )
                findNavController().navigate(action)
            }
    }

    private fun showUpcomingMovies(movies: List<Movie>) {
        binding.recyclerviewPopularTvShows.adapter =
            PopularShowsRecyclerviewAdapter(movies) { movie ->
                val action = HomeFragmentDirections.homeToDetails(
                    movieId = movie.id!!,
                    cacheId = movie.cacheId!!
                )
                findNavController().navigate(action)
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}