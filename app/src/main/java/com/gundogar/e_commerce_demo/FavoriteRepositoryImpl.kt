package com.gundogar.e_commerce_demo

import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoriteDao: FavoriteDao) : FavoriteRepository {
    override suspend fun addFavorite(product: FavoriteProduct) {
        favoriteDao.insertFavorite(product)
    }

    override suspend fun removeFavorite(product: FavoriteProduct) {
        favoriteDao.deleteFavorite(product)
    }

    override suspend fun isFavorite(productId: Int): Boolean {
        return favoriteDao.isFavorite(productId)
    }

    override suspend fun getAllFavorites(): List<FavoriteProduct> {
        return favoriteDao.getAllFavorites()
    }
}