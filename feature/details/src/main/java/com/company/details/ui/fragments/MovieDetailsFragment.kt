package com.company.details.ui.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.company.details.R
import com.company.details.databinding.FragmentMovieDetailsBinding
import com.company.details.di.loadDetailsModule
import com.company.details.ui.adapters.CastRecyclerviewAdapter
import com.company.details.ui.adapters.SimilarShowsRecyclerviewAdapter
import com.vickikbt.notflix.util.DataFormatter.getMovieDuration
import com.vickikbt.notflix.util.DataFormatter.getPopularity
import com.vickikbt.notflix.util.DataFormatter.getRating
import com.vickikbt.notflix.util.DataFormatter.getReleaseYear
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.loadImage
import com.vickikbt.notflix.util.log
import com.vickikbt.notflix.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details), StateListener {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModel()
    private fun injectFeatures() = loadDetailsModule

    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)

        injectFeatures()
        viewModel.stateListener = this


        initUI()

    }


    @SuppressLint("SetTextI18n")
    private fun initUI() {
        viewModel.getMovieDetails(args.movieId)

        binding.imageViewBack.setOnClickListener { findNavController().navigateUp() }

        viewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->
            Glide.with(requireContext())
                .load(movieDetails.backdropPath?.loadImage())
                .transition(DrawableTransitionOptions.withCrossFade(800))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Timber.e("Failed to get image bitmap")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        val imageBitmapDrawable = resource as BitmapDrawable
                        val imageBitmap = imageBitmapDrawable.bitmap

                        Palette.from(imageBitmap).maximumColorCount(20).generate { palette ->
                            val vibrantSwatch = palette?.vibrantSwatch
                            val dominantSwatch = palette?.dominantSwatch

                            if (vibrantSwatch != null) {
                                binding.felImagePoster.setBackgroundColor(vibrantSwatch.rgb)
                            } else {
                                binding.felImagePoster.setBackgroundColor(dominantSwatch!!.rgb)
                            }

                        }

                        return false
                    }
                }).into(binding.imageViewMoviePoster)

            binding.textViewMovieName.text = "${movieDetails.title}."

            if (!movieDetails.releaseDate.isNullOrEmpty()) binding.textViewMovieRelease.text =
                getReleaseYear(movieDetails.releaseDate)
            else {
                binding.textViewMovieRelease.visibility = GONE
                binding.view.visibility = GONE
            }

            binding.textViewMovieDuration.text = getMovieDuration(movieDetails.runtime ?: 0)

            binding.textViewMoviePopularity.text = getPopularity(movieDetails.voteAverage!!)
            binding.textViewMovieRating.text = "${getRating(movieDetails.voteAverage!!)}/5.0"
            binding.textViewOverview.text = movieDetails.overview

            initCastRecyclerview()

            initSimilarMoviesRecyclerview()
        }
    }

    private fun initCastRecyclerview() {
        viewModel.cast.observe(viewLifecycleOwner) { cast ->
            if (cast != null) {
                binding.recyclerviewCast.adapter = CastRecyclerviewAdapter(cast.actor!!)
            } else {
                binding.textViewCastTitle.visibility = GONE
                binding.recyclerviewCast.visibility = GONE
            }
        }
    }

    private fun initSimilarMoviesRecyclerview() {
        viewModel.similarMovies.observe(viewLifecycleOwner) { result ->
            if (result.movies!!.isEmpty()) {
                binding.textViewSimilarMoviesTitle.visibility = GONE
                binding.recyclerviewSimilarMovies.visibility = GONE
            } else {
                binding.recyclerviewSimilarMovies.adapter =
                    SimilarShowsRecyclerviewAdapter(result.movies!!)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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