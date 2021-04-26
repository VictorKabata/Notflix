package com.vickikbt.notflix.ui.fragments.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.vickikbt.data.util.DataFormatter.getMovieDuration
import com.vickikbt.data.util.DataFormatter.getPopularity
import com.vickikbt.data.util.DataFormatter.getRating
import com.vickikbt.data.util.DataFormatter.getReleaseYear
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.FragmentMovieDetailsBinding
import com.vickikbt.notflix.util.GlideUtil.getScrimPalette
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.log
import com.vickikbt.notflix.util.toast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), StateListener {

    private lateinit var binding: FragmentMovieDetailsBinding

    private val viewModel by viewModels<MovieDetailsViewModel>()

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        viewModel.stateListener = this

        //makeTransparentStatusBar(true) TODO: Implement later

        initUI()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {

        viewModel.getMovieDetails(args.movieId)

        viewModel.movieDetails.observe(viewLifecycleOwner, { movieDetails ->
            Timber.e("Movie Details: $movieDetails")
            Timber.e("Movie Details Release Date: ${movieDetails.releaseDate}")

            getScrimPalette(
                requireActivity(),
                movieDetails.backdropPath!!,
                binding.imageViewMoviePoster,
                binding.felImagePoster,
            )

            //binding.collapsingToolbar.title
            binding.textViewMovieName.text = "${movieDetails.title}."


            if (!movieDetails.releaseDate.isNullOrEmpty()) binding.textViewMovieRelease.text =
                getReleaseYear(movieDetails.releaseDate)
            else {
                binding.textViewMovieRelease.visibility = GONE
                binding.view.visibility = GONE
            }

            binding.textViewMovieDuration.text = getMovieDuration(movieDetails.runtime ?: 0)

            binding.textViewMoviePopularity.text = getPopularity(movieDetails.popularity!!)
            binding.textViewMovieRating.text = "${getRating(movieDetails.voteAverage!!)}/5.0"
            binding.textViewOverview.text = movieDetails.overview


        })
    }

    private fun makeTransparentStatusBar(isTransparent: Boolean) {
        if (isTransparent) requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        else requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun onPause() {
        super.onPause()
        makeTransparentStatusBar(false)
    }

    override fun onDestroy() {
        super.onDestroy()
        makeTransparentStatusBar(false)
    }

    override fun onLoading() {
        requireActivity().log("Loading")
    }

    override fun onSuccess(message: String) {
        requireActivity().log(message)
    }

    override fun onError(message: String?) {
        if (isAdded) {
            requireActivity().toast(message!!)
            requireActivity().log(message)
        }
    }

}