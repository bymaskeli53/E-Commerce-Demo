package com.gundogar.e_commerce_demo.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gundogar.e_commerce_demo.data.local.model.FavoriteProduct
import com.gundogar.e_commerce_demo.R
import com.gundogar.e_commerce_demo.databinding.ItemProductBinding
import com.gundogar.e_commerce_demo.core.util.toFullImageUrl

class FavoriteAdapter(private val favoriteList: List<FavoriteProduct>) :
    RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoriteViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            tvProductName.text = favoriteList[position].name
            tvProductPrice.text = buildString {
                append(favoriteList[position].price.toString())
                append(" TL")
            }

            ivProduct.load(favoriteList[position].image.toFullImageUrl()){
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error_placeholder)
                crossfade(enable = true)
                crossfade(durationMillis = 300)
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    inner class FavoriteViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}