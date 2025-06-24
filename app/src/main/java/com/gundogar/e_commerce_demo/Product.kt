package com.gundogar.e_commerce_demo

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    @SerializedName("ad")
    val name: String,
    @SerializedName("resim")
    val image: String,
    @SerializedName("kategori")
    val category: String,
    @SerializedName("fiyat")
    val price: Int,
    @SerializedName("marka")
    val brand: String
)