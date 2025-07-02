package com.gundogar.e_commerce_demo.data.remote

import com.gundogar.e_commerce_demo.data.local.model.FavoriteProduct
import com.gundogar.e_commerce_demo.data.remote.model.Product

fun Product.toFavoriteProduct() = FavoriteProduct(
    productId = this.id,
    name = this.name,
    image = this.image,
    price = this.price,
    brand = this.brand,
    category = this.category
)