package com.company.home.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.company.home.databinding.ItemPopularShowBinding
import com.company.home.utils.loadImage
import com.vickikbt.domain.models.Movie
import com.vickikbt.notflix.util.OnClick

class PopularShowsRecyclerviewAdapter constructor(
    private val showList: List<Movie>,
    private val onClick: OnClick
) :
    RecyclerView.Adapter<PopularShowsRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularShowsRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPopularShowBinding.inflate(layoutInflater, parent, false)

        return PopularShowsRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularShowsRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val movie = showList[position]

        holder.bind(context, movie)

        holder.itemView.setOnClickListener {
            onClick.onClick(movieId = movie.id)
        }
    }

    override fun getItemCount() = showList.size


}

class PopularShowsRecyclerViewHolder(private val binding: ItemPopularShowBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, movie: Movie) {

        Glide.with(context)
            .load(movie.poster_path!!.loadImage())
            .transition(DrawableTransitionOptions.withCrossFade())
            //.placeholder(R.drawable.image_placeholder)
            //.error(R.drawable.image_placeholder)
            .into(binding.imageViewShowCover)

        binding.textViewShowTitle.text = movie.title

    }

}