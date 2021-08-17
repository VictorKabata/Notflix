package com.company.home.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.company.home.databinding.ItemTopRatedMoviesBinding
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.getReleaseDate
import com.vickikbt.notflix.util.loadImage
import timber.log.Timber

class TopRatedShowsRecyclerviewAdapter constructor(
    private val showList: List<Movie>,
    private val onItemClicked: (Movie) -> Unit
) :
    RecyclerView.Adapter<TopRatedShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRatedShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTopRatedMoviesBinding.inflate(layoutInflater, parent, false)

        return TopRatedShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener { onItemClicked(movie) }
    }

    override fun getItemCount() = showList.size

}

class TopRatedShowsRecyclerViewHolder(private val binding: ItemTopRatedMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(context: Context, movie: Movie) {

        Glide.with(context)
            .load(movie.backdropPath?.loadImage())
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
                            binding.fel.setBackgroundColor(vibrantSwatch.rgb)
                            binding.textViewShowTitle.setTextColor(vibrantSwatch.titleTextColor)
                        } else {
                            binding.fel.setBackgroundColor(dominantSwatch!!.rgb)
                            binding.textViewShowTitle.setTextColor(dominantSwatch.titleTextColor)
                        }

                    }

                    return false
                }

            }).into(binding.imageViewShowCover)

        binding.textViewShowTitle.text = "${movie.title}."
        binding.ratingBarShowRating.rating = movie.voteAverage!!.getRating()
        binding.textViewReleaseDate.text = "${movie.releaseDate!!.getReleaseDate()}."
    }

}