package com.gundogar.e_commerce_demo

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("urunler")
    val products: List<Product>,
    val success: Int
)
