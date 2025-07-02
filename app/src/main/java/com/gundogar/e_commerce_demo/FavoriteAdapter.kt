package com.gundogar.e_commerce_demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gundogar.e_commerce_demo.databinding.ItemProductBinding

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
            tvProductPrice.text = favoriteList[position].price.toString()
        }
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    inner class FavoriteViewHolder(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}