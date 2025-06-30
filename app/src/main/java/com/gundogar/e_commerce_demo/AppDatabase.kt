package com.gundogar.e_commerce_demo

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteProduct::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}