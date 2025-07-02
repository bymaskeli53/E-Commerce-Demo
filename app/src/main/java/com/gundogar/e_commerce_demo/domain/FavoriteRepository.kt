package com.gundogar.e_commerce_demo.domain

import com.gundogar.e_commerce_demo.data.local.model.FavoriteProduct

interface FavoriteRepository {

    suspend fun addFavorite(product: FavoriteProduct)

    suspend fun removeFavorite(product: FavoriteProduct)

    suspend fun isFavorite(productId: Int): Boolean

    suspend fun getAllFavorites(): List<FavoriteProduct>
}