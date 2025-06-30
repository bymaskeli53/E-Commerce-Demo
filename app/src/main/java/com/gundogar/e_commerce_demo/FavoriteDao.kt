package com.gundogar.e_commerce_demo

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(product: FavoriteProduct)

    @Delete
    suspend fun deleteFavorite(product: FavoriteProduct)

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteProduct>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE productId = :productId)")
    suspend fun isFavorite(productId: Int): Boolean
}