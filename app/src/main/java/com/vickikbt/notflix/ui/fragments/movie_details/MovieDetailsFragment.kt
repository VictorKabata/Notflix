package com.vickikbt.notflix.ui.fragments.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.FragmentMovieDetailsBinding
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.log
import com.vickikbt.notflix.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), StateListener {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by viewModels<MovieDetailsViewModel>()

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        viewModel.stateListener=this

        initUI()

        return binding.root
    }

    private fun initUI() {
        val movieId=args.movieId

        //viewModel.fetchMovieDetails(791373)
    }

    override fun onLoading() {
        requireActivity().log("Loading")
    }

    override fun onSuccess(message: String) {
        requireActivity().log(message)
    }

    override fun onError(message: String) {
        requireActivity().toast(message)
        requireActivity().log(message)
    }

}