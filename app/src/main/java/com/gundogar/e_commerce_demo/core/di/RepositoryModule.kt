package com.gundogar.e_commerce_demo.core.di

import com.gundogar.e_commerce_demo.domain.FavoriteRepository
import com.gundogar.e_commerce_demo.data.repository.FavoriteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFavouritesRepository(
        todosRepositoryImpl: FavoriteRepositoryImpl
    ): FavoriteRepository


}