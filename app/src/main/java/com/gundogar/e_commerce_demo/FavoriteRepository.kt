package com.gundogar.e_commerce_demo

interface FavoriteRepository {

    suspend fun addFavorite(product: FavoriteProduct)

    suspend fun removeFavorite(product: FavoriteProduct)

    suspend fun isFavorite(productId: Int): Boolean

    suspend fun getAllFavorites(): List<FavoriteProduct>
}