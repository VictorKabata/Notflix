package com.company.details.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.company.details.R
import com.company.details.databinding.ItemSimilarShowBinding
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.getRating
import com.vickikbt.notflix.util.loadImage

class SimilarShowsRecyclerviewAdapter constructor(
    private val showList: List<Movie>
) :
    RecyclerView.Adapter<SimilarShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSimilarShowBinding.inflate(layoutInflater, parent, false)

        return SimilarShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener {
            //onClick.onClick(movieId = movie.id!!)
        }
    }

    override fun getItemCount() = showList.size


}

class SimilarShowsRecyclerViewHolder(private val binding: ItemSimilarShowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, movie: Movie) {

        Glide.with(context)
            .load(movie.posterPath?.loadImage())
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.imageViewShowCover)

        binding.textViewShowTitle.text = movie.title
        binding.ratingBarShowRating.rating = movie.voteAverage!!.getRating()

    }

}