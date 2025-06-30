package com.gundogar.e_commerce_demo

fun Product.toFavoriteProduct() = FavoriteProduct(
    productId = this.id,
    name = this.name,
    image = this.image,
    price = this.price,
    brand = this.brand,
    category = this.category
)