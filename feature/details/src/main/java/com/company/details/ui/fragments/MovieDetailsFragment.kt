package com.company.details.ui.fragments

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import android.view.View.GONE
import android.view.animation.AnimationUtils
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.palette.graphics.Palette
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
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
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.vickikbt.domain.models.Cast
import com.vickikbt.domain.models.MovieDetails
import com.vickikbt.domain.models.MovieVideo
import com.vickikbt.domain.models.SimilarMovies
import com.vickikbt.domain.utils.Constants.YT_VIDEO_URL
import com.vickikbt.notflix.util.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MovieDetailsFragment : Fragment(R.layout.fragment_movie_details) {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModel()
    private fun injectFeatures() = loadDetailsModule

    private val args by navArgs<MovieDetailsFragmentArgs>()

    private var simpleExoPlayer: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    private var videoKey: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailsBinding.bind(view)

        injectFeatures()

        initUI()

    }

    private fun initUI() {
        viewModel.getMovieDetails(movieId = args.movieId)
        viewModel.isMovieFavorite(movieId = args.movieId)
        viewModel.getMovieCast(movieId = args.movieId)
        viewModel.getMovieVideos(movieId = args.movieId)
        viewModel.getSimilarMovies(movieId = args.movieId)

        binding.imageViewBack.setOnClickListener { findNavController().navigateUp() }

        binding.fabPlayTrailer.setOnClickListener { initVideoPlayer() }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.isMovieFavorite.collect { uiState ->
                        when (uiState) {
                            is MovieDetailsViewModel.IsMovieFavoriteUiState.Error -> showError(
                                uiState.error
                            )
                            is MovieDetailsViewModel.IsMovieFavoriteUiState.Success -> showIsMovieFavorite(
                                uiState.isMovieFavorite
                            )
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.movieDetails.collect { uiState ->
                        when (uiState) {
                            is MovieDetailsViewModel.MovieDetailsUiState.Error -> showError(uiState.error)
                            is MovieDetailsViewModel.MovieDetailsUiState.Success -> showMovieDetails(
                                uiState.movieDetails
                            )
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.cast.collect { uiState ->
                        when (uiState) {
                            is MovieDetailsViewModel.MovieCastUiState.Error -> showError(uiState.error)
                            is MovieDetailsViewModel.MovieCastUiState.Success -> showMovieCast(
                                uiState.movieCast
                            )
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.videos.collect { uiState ->
                        when (uiState) {
                            is MovieDetailsViewModel.MovieVideosUiState.Error -> showError(uiState.error)
                            is MovieDetailsViewModel.MovieVideosUiState.Success -> showMovieVideos(
                                uiState.videos
                            )
                            else -> showLoading()
                        }
                    }
                }

                launch {
                    viewModel.similarMovies.collect { uiState ->
                        when (uiState) {
                            is MovieDetailsViewModel.SimilarMoviesUiState.Error -> showError(uiState.error)
                            is MovieDetailsViewModel.SimilarMoviesUiState.Success -> showSimilarMovies(
                                uiState.similarMovies
                            )
                            else -> showLoading()
                        }
                    }
                }

            }
        }
    }

    private fun showError(errorMessage: String) {
        Timber.e("An error occurred: $errorMessage")
    }

    private fun showLoading() {

    }

    private fun showIsMovieFavorite(isFavorite: Boolean?) {
        val favUnselected =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_fav_unselected, null)
        val favSelected = ResourcesCompat.getDrawable(resources, R.drawable.ic_fav_selected, null)

        val zoomInAnim = AnimationUtils.loadAnimation(context, R.anim.zoom_in)
        if (isFavorite != null && isFavorite) {
            binding.imageViewFavorite.setImageDrawable(favSelected)

            binding.imageViewFavorite.setOnClickListener {
                updateMovieFavorite(false)
                binding.imageViewFavorite.setImageDrawable(favUnselected)
                it.startAnimation(zoomInAnim)
            }
        } else {
            binding.imageViewFavorite.setImageDrawable(favUnselected)

            binding.imageViewFavorite.setOnClickListener {
                updateMovieFavorite(true)
                binding.imageViewFavorite.setImageDrawable(favSelected)
                it.startAnimation(zoomInAnim)
            }
        }
    }

    private fun showMovieDetails(movieDetails: MovieDetails) {
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

        binding.textViewMovieName.text = movieDetails.title

        if (!movieDetails.releaseDate.isNullOrEmpty()) binding.textViewMovieRelease.text =
            movieDetails.releaseDate!!.getReleaseYear()
        else {
            binding.textViewMovieRelease.visibility = GONE
            binding.view.visibility = GONE
        }

        binding.textViewMovieDuration.text = movieDetails.runtime?.getMovieDuration()

        binding.textViewMoviePopularity.text = movieDetails.voteAverage!!.getPopularity()
        binding.textViewMovieRating.text =
            "${movieDetails.voteAverage?.getRating()}" //"${getRating()}/5.0"
        binding.textViewOverview.text = movieDetails.overview
    }

    private fun showMovieCast(cast: Cast?) {
        if (cast != null) {
            binding.recyclerviewCast.adapter = CastRecyclerviewAdapter(cast.actor!!)
        } else {
            binding.textViewCastTitle.hide()
            binding.recyclerviewCast.hide()
        }
    }

    private fun showMovieVideos(movieVideo: MovieVideo) {
        val filteredVideos=movieVideo.videos?.filter { it.site == "YouTube" && it.type == "Trailer" || it.type == "Teaser" }

        filteredVideos?.forEach {
            Timber.e("Filtered videos: $it")
        }

        videoKey = filteredVideos!![0].key

        //initVideoPlayer()
    }

    private fun initVideoPlayer() {
        simpleExoPlayer = SimpleExoPlayer.Builder(requireContext()).build().also {exoplayer->
            binding.playerViewDetails.player = exoplayer
        }

        val videoUrl = "$YT_VIDEO_URL${videoKey}"

        val mediaItem = MediaItem.fromUri(" https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
        simpleExoPlayer!!.setMediaItem(mediaItem)


        Timber.e("Video URL to be played: $videoUrl")

        /*object : YouTubeExtractor(requireContext()) {
            override fun onExtractionComplete(
                ytFiles: SparseArray<YtFile>?,
                videoMeta: VideoMeta?
            ) {
                if (ytFiles != null) {
                    val videoTag = 137
                    val audioTag = 140
                    val vidUrl = ytFiles[videoTag].url
                    val audioUrl = ytFiles[audioTag].url

                    val audioSource: MediaSource = ProgressiveMediaSource
                            .Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(audioUrl))

                    val videoSource: MediaSource = ProgressiveMediaSource
                        .Factory(DefaultHttpDataSource.Factory())
                        .createMediaSource(MediaItem.fromUri(vidUrl))

                    simpleExoPlayer!!.setMediaSource(MergingMediaSource(true,videoSource,audioSource), true)
                    simpleExoPlayer!!.prepare()
                    simpleExoPlayer!!.playWhenReady=playWhenReady
                    simpleExoPlayer!!.seekTo(currentWindow,playbackPosition)
                }
            }
        }.extract(videoUrl, false, true)*/


    }

    private fun releaseVideoPlayer() {
        if (simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer!!.playWhenReady
            playbackPosition = simpleExoPlayer!!.currentPosition
            currentWindow = simpleExoPlayer!!.currentWindowIndex
            simpleExoPlayer!!.release()
            simpleExoPlayer = null
        }
    }

    private fun showSimilarMovies(similarMovies: SimilarMovies) {
        if (similarMovies.movies!!.isEmpty()) {
            binding.textViewSimilarMoviesTitle.visibility = GONE
            binding.recyclerviewSimilarMovies.visibility = GONE
        } else {
            binding.recyclerviewSimilarMovies.adapter =
                SimilarShowsRecyclerviewAdapter(similarMovies.movies!!)
        }
    }

    private fun updateMovieFavorite(isFavorite: Boolean) =
        viewModel.updateIsMovieFavorite(cacheId = args.cacheId, isFavorite = isFavorite)

    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) initVideoPlayer()
    }

    override fun onResume() {
        super.onResume()
        if (Util.SDK_INT >= 24 || simpleExoPlayer == null) initVideoPlayer()
    }

    override fun onPause() {
        if (Util.SDK_INT < 24) releaseVideoPlayer()
        super.onPause()
    }

    override fun onStop() {
        if (Util.SDK_INT < 24) releaseVideoPlayer()
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}