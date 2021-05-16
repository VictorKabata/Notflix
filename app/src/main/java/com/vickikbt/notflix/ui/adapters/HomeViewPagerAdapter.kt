package com.vickikbt.notflix.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.vickikbt.data.util.DataFormatter.getRating
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ItemHomeViewpagerBinding
import com.vickikbt.notflix.ui.fragments.home.HomeFragmentDirections
import com.vickikbt.notflix.util.GlideUtil.getScrimPalette

class HomeViewPagerAdapter constructor(
    private val context: Context,
    private val showsList: List<Movie>
) : PagerAdapter() {

    override fun getCount() = 5 //games.size

    override fun isViewFromObject(view: View, `object`: Any) = view.equals(`object`)

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemHomeViewpagerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_home_viewpager, container, false)

        //val randomPosition = Random(position).nextInt()
        val show = showsList[position]

        getScrimPalette(
            context,
            show.backdrop_path!!,
            binding.imageViewHomeSliderBackground,
            binding.felTrendingShows,
        )

        binding.textViewTrendingShows.text = "${show.title}."
        binding.ratingBarTrendingShows.rating = getRating(show.vote_average)

        container.addView(binding.root, 0)

        binding.textViewTrendingShows.setOnClickListener {
            val action = HomeFragmentDirections.homeToMovieDetails(show.id)
            it.findNavController().navigate(action)
        }

        /*binding.imageViewHomeSliderBackground.setOnClickListener {
            val action=HomeFragmentDirections.homeToMovieDetails(show.id)
            it.findNavController().navigate(action)
        }*/

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}