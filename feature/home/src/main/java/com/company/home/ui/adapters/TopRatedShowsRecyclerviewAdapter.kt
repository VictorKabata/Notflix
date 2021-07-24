package com.company.home.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company.home.databinding.ItemTopRatedShowBinding
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.DataFormatter.getRating
import com.vickikbt.notflix.util.DataFormatter.getReleaseDate
import com.vickikbt.notflix.util.OnClick

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
        val binding= ItemTopRatedShowBinding.inflate(layoutInflater, parent, false)

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

        binding.textViewShowTitle.text = "${movie.title}."
        binding.ratingBarShowRating.rating = getRating(movie.vote_average)
        binding.textViewReleaseDate.text = "${getReleaseDate(movie.release_date)}."
    }

}