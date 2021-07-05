package com.company.details.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.core.DataFormatter
import com.vickikbt.core.DataFormatter.loadImage
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ItemSimilarShowBinding
import com.vickikbt.notflix.util.OnClick
import com.vickikbt.repository.models.Movie

class SimilarShowsRecyclerviewAdapter constructor(
    private val showList: List<Movie>,
    private val onClick: OnClick
) :
    RecyclerView.Adapter<SimilarShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSimilarShowBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_similar_show, parent, false)

        return SimilarShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener {
            onClick.onClick(movieId = movie.id)
        }
    }

    override fun getItemCount() = showList.size


}

class SimilarShowsRecyclerViewHolder(private val binding: ItemSimilarShowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, movie: Movie) {

        Glide.with(context)
            .load(loadImage(movie.poster_path))
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.image_placeholder)
            .error(R.drawable.image_placeholder)
            .into(binding.imageViewShowCover)

        binding.textViewShowTitle.text = movie.title
        binding.ratingBarShowRating.rating = DataFormatter.getRating(movie.vote_average)

    }

}