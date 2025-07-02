package com.gundogar.e_commerce_demo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gundogar.e_commerce_demo.data.local.model.FavoriteProduct

@Database(entities = [FavoriteProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}