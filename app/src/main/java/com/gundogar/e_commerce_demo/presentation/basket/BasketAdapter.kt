package com.gundogar.e_commerce_demo.presentation.basket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gundogar.e_commerce_demo.data.remote.model.BasketProduct
import com.gundogar.e_commerce_demo.R
import com.gundogar.e_commerce_demo.databinding.ItemBasketBinding
import com.gundogar.e_commerce_demo.core.util.toFullImageUrl


private const val FADE_OUT_ANIMATION_DURATION = 700L

class BasketAdapter(
    private val onItemDelete: (BasketProduct) -> Unit
) : ListAdapter<BasketProduct, BasketAdapter.BasketViewHolder>(BasketProductDiffCallback) {


    inner class BasketViewHolder(val binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val product = getItem(position)

        with(holder.binding) {
            ivBasketProductImage.load(product.image.toFullImageUrl()) {
                crossfade(true)
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error_placeholder)
            }

            tvBasketProductName.text = product.name
            tvBasketQuantity.text = product.numberOfOrders.toString()
            tvBasketProductPrice.text = buildString {
                append(product.price.toString())
                append(" TL")
            }
            tvTotalPrice.text = buildString {
                append((product.numberOfOrders * product.price).toString())
                append(" TL")
            }

            btnDeleteProduct.setOnClickListener {
                holder.itemView.animate()
                    .alpha(0f)
                    .setDuration(FADE_OUT_ANIMATION_DURATION)
                    .withEndAction {
                        onItemDelete(product)
                    }
                    .start()
            }
        }
    }

    object BasketProductDiffCallback : DiffUtil.ItemCallback<BasketProduct>() {
        override fun areItemsTheSame(oldItem: BasketProduct, newItem: BasketProduct): Boolean {
            return oldItem.basketId == newItem.basketId
        }

        override fun areContentsTheSame(oldItem: BasketProduct, newItem: BasketProduct): Boolean {
            return oldItem == newItem
        }
    }
}