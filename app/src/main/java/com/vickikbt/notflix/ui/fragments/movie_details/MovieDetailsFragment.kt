package com.vickikbt.notflix.ui.fragments.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.vickikbt.data.util.DataFormatter.getMovieDuration
import com.vickikbt.data.util.DataFormatter.getPopularity
import com.vickikbt.data.util.DataFormatter.getRating
import com.vickikbt.data.util.DataFormatter.getReleaseYear
import com.vickikbt.data.util.DataFormatter.getYoutubeVideoFromUrl
import com.vickikbt.data.util.DataFormatter.loadImage
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.VideoItem
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.FragmentMovieDetailsBinding
import com.vickikbt.notflix.ui.adapters.CastRecyclerviewAdapter
import com.vickikbt.notflix.ui.adapters.SimilarShowsRecyclerviewAdapter
import com.vickikbt.notflix.util.GlideUtil.getScrimPalette
import com.vickikbt.notflix.util.OnClick
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.log
import com.vickikbt.notflix.util.toast
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation


@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), StateListener, OnClick {

    private lateinit var binding: FragmentMovieDetailsBinding
    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()

    private lateinit var mediaController: MediaController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false)
        viewModel.stateListener = this

        //makeTransparentStatusBar()

        initUI()

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        viewModel.getMovieDetails(args.movieId)

        binding.imageViewBack.setOnClickListener { findNavController().navigateUp() }

        viewModel.movieDetails.observe(viewLifecycleOwner) { movieDetails ->
            getScrimPalette(
                requireActivity(),
                movieDetails.backdropPath!!,
                binding.imageViewMoviePoster,
                binding.felImagePoster,
            )

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
                CastRecyclerviewAdapter(cast.castItem)
            else {
                binding.textViewCastTitle.visibility = GONE
                binding.recyclerviewCast.visibility = GONE
            }
        }
    }

    private fun initVideoPlayer(movieDetails: MovieDetails) {
        viewModel.video.observe(viewLifecycleOwner) { videos ->
            val video = videos.videoItems[0]

            Glide.with(requireActivity())
                .load(loadImage(movieDetails.backdropPath))
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 2)))
                .into(binding.imageViewVideoPlaceholder)

            binding.fabPlayTrailer.setOnClickListener {
                initYoutubePlayer(video)
            }
        }
    }

    private fun initYoutubePlayer(videoItem: VideoItem) {
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
                    requireActivity().log("YT initialization failed!: ${youTubeInitializationResult?.name}")
                    requireActivity().toast("Failed to load trailer")
                }

            })
    }

    private fun initSimilarMoviesRecyclerview() {
        viewModel.similarMovies.observe(viewLifecycleOwner) { result ->
            if (result.movies == null) {
                binding.textViewSimilarMoviesTitle.visibility = GONE
                binding.recyclerviewSimilarMovies.visibility = GONE
            } else {
                binding.recyclerviewSimilarMovies.adapter =
                    SimilarShowsRecyclerviewAdapter(result.movies!!, this)
            }
        }
    }

    private fun makeTransparentStatusBar(isTransparent: Boolean = true) {
        if (isTransparent) requireActivity().window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        else requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    override fun onDestroy() {
        super.onDestroy()
        //makeTransparentStatusBar(false)
    }

    override fun onPause() {
        super.onPause()
        //makeTransparentStatusBar(false)
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