package com.vickikbt.notflix.util

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bosphere.fadingedgelayout.FadingEdgeLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.vickikbt.core.DataFormatter.loadImage
import com.vickikbt.notflix.R

object GlideUtil {

    //Sets image to imageView and sets vibrant color to the FEL
    fun getFelPalette(
        context: Context,
        imageUrl: String,
        imageView: ImageView,
        fel: FadingEdgeLayout,
    ) {
        var color: Int?

        Glide.with(context)
            .load(loadImage(imageUrl))
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("VickiKbt", "onLoadFailed: ${e!!.message.toString()}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val drawable = resource as BitmapDrawable
                    val bitmap = drawable.bitmap
                    Palette.Builder(bitmap).maximumColorCount(32).generate { palette ->

                        //TODO: Add check for app theme and change default color to light or dark depending on theme.
                        color = palette!!.getVibrantColor(
                            ContextCompat.getColor(
                                context,
                                R.color.primaryTextColor
                            )
                        )
                        fel.setBackgroundColor(color!!)
                    }

                    return false
                }

            }).into(imageView)
    }

    fun getScrimPalette(
        context: Context,
        imageUrl: String,
        imageView: ImageView,
        fel: FadingEdgeLayout
    ) {
        var color: Int?

        Glide.with(context)
            .load(loadImage(imageUrl))
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("VickiKbt", "onLoadFailed: ${e!!.message.toString()}")
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val drawable = resource as BitmapDrawable
                    val bitmap = drawable.bitmap
                    Palette.Builder(bitmap).maximumColorCount(32).generate { palette ->

                        //TODO: Add check for app theme and change default color to light or dark depending on theme.
                        color = palette!!.getVibrantColor(
                            ContextCompat.getColor(
                                context,
                                R.color.primaryTextColor
                            )
                        )

                        color.let {
                            fel.setBackgroundColor(it!!)
                        }
                    }

                    return false
                }

            }).into(imageView)
    }


}