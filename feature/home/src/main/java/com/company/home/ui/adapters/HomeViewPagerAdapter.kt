package com.company.home.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.company.home.R
import com.company.home.databinding.ItemHomeViewpagerBinding
import com.company.home.utils.loadImage
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.DataFormatter.getRating
import timber.log.Timber

class HomeViewPagerAdapter constructor(
    private val context: Context,
    private val showsList: List<Movie>
) : PagerAdapter() {

    override fun getCount() = 5 //shows.size

    override fun isViewFromObject(view: View, `object`: Any) = view.equals(`object`)

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemHomeViewpagerBinding.inflate(layoutInflater, container, false)

        val show = showsList[position]

        Glide.with(context)
            .load(show.backdrop_path!!.loadImage())
            //.placeholder(R.drawable.image_placeholder)
            //.error(R.drawable.image_placeholder)
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

                    Palette.from(imageBitmap).generate { palette ->
                        val vibrantSwatch = palette?.vibrantSwatch
                        val dominantSwatch = palette?.dominantSwatch


                    }

                    return false
                }

            }).into(binding.imageViewHomeSliderBackground)

        binding.textViewTrendingShows.text = "${show.title}."
        binding.ratingBarTrendingShows.rating = getRating(show.vote_average)

        container.addView(binding.root, 0)

        binding.textViewTrendingShows.setOnClickListener {
            //val action = HomeFragmentDirections.homeToMovieDetails(show.id)
            //it.findNavController().navigate(action)
        }


        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}