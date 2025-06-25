package com.gundogar.e_commerce_demo

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable