package com.company.details.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.company.details.R
import com.company.details.databinding.ItemCastBinding
import com.vickikbt.domain.models.Actor
import com.vickikbt.notflix.util.DataFormatter.loadImage
import com.vickikbt.notflix.util.loadImage

class CastRecyclerviewAdapter constructor(
    private val actors: List<Actor>,
) :
    RecyclerView.Adapter<CastRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCastBinding.inflate(layoutInflater, parent, false)

        return CastRecyclerViewHolder(binding)
    }

    override fun getItemCount() = actors.size

    override fun onBindViewHolder(holder: CastRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val cast = actors[position]

        holder.bind(context, cast)

    }


}

class CastRecyclerViewHolder(private val binding: ItemCastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, actor: Actor) {

        Glide.with(context)
            .load(actor.profilePath?.loadImage())
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_actor)
            .error(R.drawable.ic_actor)
            .into(binding.imageViewActorProfile)

        binding.textViewActorName.text = actor.name
        binding.textViewActorCharacterName.text = actor.character

    }

}