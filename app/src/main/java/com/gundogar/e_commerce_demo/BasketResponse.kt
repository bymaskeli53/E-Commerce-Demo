package com.gundogar.e_commerce_demo


import com.google.gson.annotations.SerializedName

data class BasketResponse(
    @SerializedName("success")
    val success: Int,
    @SerializedName("urunler_sepeti")
    val basketProducts: List<BasketProduct>
)