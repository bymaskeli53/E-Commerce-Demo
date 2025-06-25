package com.gundogar.e_commerce_demo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gundogar.e_commerce_demo.databinding.ItemBasketBinding


class BasketAdapter(
    private val productList: List<BasketProduct>,
    private val onItemClick: (BasketProduct) -> Unit
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    inner class BasketViewHolder(val binding: ItemBasketBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        val product = productList[position]
        with(holder.binding) {
            ivBasketProductImage.load(product.image.toFullImageUrl()) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_background)
                error(R.drawable.ic_launcher_foreground)

            }
            tvBasketProductName.text = product.name
            tvBasketQuantity.text = product.numberOfOrders.toString()
            tvBasketProductPrice.text = product.price.toString()
            tvTotalPrice.text = (product.numberOfOrders * product.price).toString()
            btnDeleteProduct.setOnClickListener {
                onItemClick(product)
            }

        }

    }

    override fun getItemCount(): Int = productList.size
}
