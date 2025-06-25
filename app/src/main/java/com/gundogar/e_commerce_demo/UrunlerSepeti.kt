package com.gundogar.e_commerce_demo


import com.google.gson.annotations.SerializedName

data class BasketProduct(
    @SerializedName("ad")
    val name: String,
    @SerializedName("fiyat")
    val price: Int,
    @SerializedName("kategori")
    val category: String,
    @SerializedName("kullaniciAdi")
    val userName: String,
    @SerializedName("marka")
    val brand: String,
    @SerializedName("resim")
    val image: String,
    @SerializedName("sepetId")
    val basketId: Int,
    @SerializedName("siparisAdeti")
    val numberOfOrders: Int
)