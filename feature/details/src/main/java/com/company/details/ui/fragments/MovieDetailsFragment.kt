package com.company.details.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.company.details.R
import com.company.details.databinding.FragmentMovieDetailsBinding
import com.company.details.di.loadDetailsModule
import com.company.details.ui.adapters.CastRecyclerviewAdapter
import com.company.details.ui.adapters.SimilarShowsRecyclerviewAdapter
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.notflix.util.DataFormatter.getMovieDuration
import com.vickikbt.notflix.util.DataFormatter.getPopularity
import com.vickikbt.notflix.util.DataFormatter.getRating
import com.vickikbt.notflix.util.DataFormatter.getReleaseYear
import com.vickikbt.notflix.util.OnClick
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.log
import com.vickikbt.notflix.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details), StateListener, OnClick {

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
            /*getScrimPalette(
                requireActivity(),
                movieDetails.backdropPath!!,
                binding.imageViewMoviePoster,
                binding.felImagePoster,
            )*/

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

            initVideoPlayer(movieDetails)

            initSimilarMoviesRecyclerview()
        }
    }

    private fun initCastRecyclerview() {
        viewModel.cast.observe(viewLifecycleOwner) { cast ->
            if (cast != null) binding.recyclerviewCast.adapter =
                CastRecyclerviewAdapter(cast.castItem!!)
            else {
                binding.textViewCastTitle.visibility = GONE
                binding.recyclerviewCast.visibility = GONE
            }
        }
    }

    private fun initVideoPlayer(movieDetails: MovieDetails) {
        /*viewModel.video.observe(viewLifecycleOwner) { videos ->
            val video = videos.videoItems!![0]

            Glide.with(requireActivity())
                .load(loadImage(movieDetails.backdropPath))
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 2)))
                .into(binding.imageViewVideoPlaceholder)

            binding.fabPlayTrailer.setOnClickListener {
                //initYoutubePlayer(video)
            }
        }*/
    }

    /*private fun initYoutubePlayer(videoItem: VideoItem) {
        requireActivity().log("Starting Youtube player")

        requireActivity().log("Video Path: https://www.youtube.com/watch?v=${videoItem.key}")

        val videoPath = "https://www.youtube.com/watch?v=${videoItem.key}"

        val youTubeBaseActivity = YouTubeBaseActivity()
        val youtubePlayer = YouTubePlayerView(youTubeBaseActivity)
        youtubePlayer.initialize(
            resources.getString(R.string.yt_player_api_key),
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    youtubePlayerProvider: YouTubePlayer.Provider?,
                    youtubePlayer: YouTubePlayer?,
                    isInitialized: Boolean
                ) {
                    youtubePlayer!!.loadVideo(getYoutubeVideoFromUrl(videoPath))
                    youtubePlayer.play()
                }

                override fun onInitializationFailure(
                    youtubePlayerProvider: YouTubePlayer.Provider?,
                    youTubeInitializationResult: YouTubeInitializationResult?
                ) {
                    requireActivity().log("YT initialization failed!: ${youTubeInitializationResult.name}")
                    requireActivity().toast("Failed to load trailer")
                }

            })
    }*/

    private fun initSimilarMoviesRecyclerview() {
        viewModel.similarMovies.observe(viewLifecycleOwner) { result ->
            if (result.movies!!.isEmpty()) {
                binding.textViewSimilarMoviesTitle.visibility = GONE
                binding.recyclerviewSimilarMovies.visibility = GONE
            } else {
                binding.recyclerviewSimilarMovies.adapter =
                    SimilarShowsRecyclerviewAdapter(result.movies!!, this)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(movieId: Int) {

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