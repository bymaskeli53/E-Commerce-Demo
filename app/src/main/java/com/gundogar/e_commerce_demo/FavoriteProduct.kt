package com.gundogar.e_commerce_demo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteProduct(
    @PrimaryKey val productId: Int,
    val name: String,
    val image: String,
    val price: Int,
    val brand: String,
    val category: String
)
