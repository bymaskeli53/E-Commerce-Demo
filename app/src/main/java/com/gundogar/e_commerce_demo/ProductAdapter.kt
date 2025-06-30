package com.gundogar.e_commerce_demo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gundogar.e_commerce_demo.databinding.ItemHomeProductBinding

class ProductAdapter(
    private val onItemClick: (Product) -> Unit,
    private val onFavoriteClick: (Product) -> Unit,
    private val isFavorite: (Int) -> Boolean // Higher-order function
) : ListAdapter<Product, ProductAdapter.ProductViewHolder>(ProductDiffCallback()) {

    inner class ProductViewHolder(val binding: ItemHomeProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemHomeProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)

        with(holder.binding) {
            tvProductName.text = product.name
            tvProductPrice.text = "${product.price} TL"

            ivProduct.load(product.image.toFullImageUrl()) {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error_placeholder)
            }

            // Favori durumunu kontrol et ve ikonu ayarla
            val isFavorited = isFavorite(product.id)
            ivFavorite.setImageResource(
                if (isFavorited) R.drawable.ic_favorite else R.drawable.ic_unfavorite
            )

            ivFavorite.setOnClickListener {
                onFavoriteClick(product)

                // Animasyon ekle
                val animation = AnimationUtils.loadAnimation(root.context, R.anim.heart_toggle)
                ivFavorite.startAnimation(animation)
            }

            root.setOnClickListener {
                onItemClick(product)
            }
        }
    }

    class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}

