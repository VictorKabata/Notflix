package com.company.home.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vickikbt.core.DataFormatter.getRating
import com.vickikbt.core.DataFormatter.getReleaseDate
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ItemTopRatedShowBinding
import com.vickikbt.notflix.util.GlideUtil.getFelPalette
import com.vickikbt.notflix.util.OnClick
import com.vickikbt.repository.models.Movie

class TopRatedShowsRecyclerviewAdapter constructor(
    private val showList: List<Movie>,
    private val onClick: OnClick
) :
    RecyclerView.Adapter<TopRatedShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRatedShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTopRatedShowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_top_rated_show, parent, false)

        return TopRatedShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopRatedShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener { onClick.onClick(movieId = movie.id) }
    }

    override fun getItemCount() = showList.size

}

class TopRatedShowsRecyclerViewHolder(private val binding: ItemTopRatedShowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(context: Context, movie: Movie) {

        getFelPalette(context, movie.backdrop_path!!, binding.imageViewShowCover, binding.fel)

        binding.textViewShowTitle.text = "${movie.title}."
        binding.ratingBarShowRating.rating = getRating(movie.vote_average)
        binding.textViewReleaseDate.text = "${getReleaseDate(movie.release_date)}."
    }

}