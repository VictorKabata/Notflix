package com.company.home.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.company.home.databinding.ItemHomeViewpagerBinding
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.DataFormatter.getRating

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