package com.company.favorites.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.company.favorites.databinding.ItemFavoriteMoviesBinding
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.loadImage

class FavoriteMoviesRecyclerviewAdapter constructor(
    private val showList: List<Movie>,
    private val onItemClicked: (Movie) -> Unit
) :
    RecyclerView.Adapter<FavoriteMoviesRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMoviesRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFavoriteMoviesBinding.inflate(layoutInflater, parent, false)

        return FavoriteMoviesRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener { onItemClicked(movie) }
    }

    override fun getItemCount() = showList.size


}

class FavoriteMoviesRecyclerViewHolder(private val binding: ItemFavoriteMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, movie: Movie) {

        Glide.with(context)
            .load(movie.posterPath?.loadImage())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageViewShowCover)

        binding.textViewShowTitle.text = movie.title

    }

}