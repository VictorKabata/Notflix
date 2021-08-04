package com.company.home.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.company.home.databinding.ItemPopularMoviesBinding
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.loadImage

class PopularShowsRecyclerviewAdapter constructor(
    private val showList: List<Movie>,
    private val onItemClicked: (Movie) -> Unit
) :
    RecyclerView.Adapter<PopularShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =ItemPopularMoviesBinding.inflate(layoutInflater, parent, false)

        return PopularShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener { onItemClicked(movie) }
    }

    override fun getItemCount() = showList.size


}

class PopularShowsRecyclerViewHolder(private val binding: ItemPopularMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, movie: Movie) {

        Glide.with(context)
            .load(movie.posterPath?.loadImage())
            .transition(DrawableTransitionOptions.withCrossFade())
            //.placeholder(R.drawable.image_placeholder)
            //.error(R.drawable.image_placeholder)
            .into(binding.imageViewShowCover)

        binding.textViewShowTitle.text = movie.title

    }

}