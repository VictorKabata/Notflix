package com.company.home.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.home.R
import com.company.home.databinding.FragmentHomeBinding
import com.company.home.di.loadHomeModule
import com.company.home.ui.adapters.HomeViewPagerAdapter
import com.company.home.ui.adapters.PopularShowsRecyclerviewAdapter
import com.company.home.ui.adapters.TopRatedShowsRecyclerviewAdapter
import com.vickikbt.notflix.util.OnClick
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.log
import com.vickikbt.notflix.util.toast
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), StateListener, OnClick {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModel()
    private fun injectFeatures() = loadHomeModule

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        injectFeatures()
        viewModel.stateListener = this

        initUI()

        return binding.root
    }

    private fun initUI() {

        //TODO: Loads up trending movies list to viewpager adapter
        viewModel.upcomingMovies.observe(viewLifecycleOwner, { result ->
            binding.viewPagerTrendingShows.adapter =
                HomeViewPagerAdapter(requireActivity(), result.movies!!)
            binding.dotsTrendingShows.setViewPager(binding.viewPagerTrendingShows)
        })

        //Loads up popular movies list to recyclerview adapter
        viewModel.popularMovies.observe(viewLifecycleOwner, { result ->
            binding.recyclerviewPopularMovies.adapter =
                PopularShowsRecyclerviewAdapter(result.movies!!, this)
        })

        //TODO: Loads up top rated movies list to recyclerview adapter
        viewModel.upcomingMovies.observe(viewLifecycleOwner, { result ->
            binding.recyclerviewTopRatedMovies.adapter =
                TopRatedShowsRecyclerviewAdapter(result.movies!!, this)
        })

        //Loads up popular tv shows list to recyclerview adapter
        viewModel.upcomingMovies.observe(viewLifecycleOwner, { result ->
            binding.recyclerviewPopularTvShows.adapter =
                PopularShowsRecyclerviewAdapter(result.movies!!, this)
        })

    }

    override fun onClick(movieId: Int) {
        //val action=HomeFragmentDirections.homeToDetails(movieId)
        findNavController().navigate(R.id.home_to_details)
    }


    override fun onLoading() {
        requireActivity().log("Loading...")
    }

    override fun onSuccess(message: String) {
        requireActivity().log(message)
    }

    override fun onError(message: String?) {
        requireActivity().toast(message!!)
        requireActivity().log(message)
    }

}