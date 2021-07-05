package com.company.details.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vickikbt.core.DataFormatter.loadImage
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ItemCastBinding
import com.vickikbt.repository.models.CastItem

class CastRecyclerviewAdapter constructor(
    private val castList: List<CastItem>,
) :
    RecyclerView.Adapter<CastRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CastRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemCastBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_cast, parent, false)

        return CastRecyclerViewHolder(binding)
    }

    override fun getItemCount() = castList.size

    override fun onBindViewHolder(holder: CastRecyclerViewHolder, position: Int) {
        val context = holder.itemView.context
        val cast = castList[position]

        holder.bind(context, cast)

    }


}

class CastRecyclerViewHolder(private val binding: ItemCastBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(context: Context, cast: CastItem) {

        Glide.with(context)
            .load(loadImage(cast.profile_path))
            .circleCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .placeholder(R.drawable.ic_actor)
            .error(R.drawable.ic_actor)
            .into(binding.imageViewActorProfile)

        binding.textViewActorName.text = cast.name
        binding.textViewActorCharacterName.text = cast.character

    }

}