package com.vickikbt.notflix.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vickikbt.data.util.DataFormatter.dateFormatter
import com.vickikbt.data.util.DataFormatter.getRating
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.TopRatedShowItemBinding
import com.vickikbt.notflix.util.GlideUtil.getFelPalette

class TopRatedShowsRecyclerviewAdapter constructor(private val showList: List<Movie>) :
    RecyclerView.Adapter<TopRatedShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRatedShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: TopRatedShowItemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.top_rated_show_item, parent, false)

        return TopRatedShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)
    }

    override fun getItemCount() = showList.size

}

class TopRatedShowsRecyclerViewHolder(private val binding: TopRatedShowItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(context: Context, movie: Movie) {

        getFelPalette(context, movie.backdrop_path, binding.imageViewShowCover, binding.fel)

        binding.textViewShowTitle.text = "${movie.title}."
        binding.ratingBarShowRating.rating = getRating(movie.vote_average)
        binding.textViewReleaseDate.text = "${dateFormatter(movie.release_date)}."
    }

}