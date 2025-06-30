package com.gundogar.e_commerce_demo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

//    init {
//        loadFavorites()
//    }

    private val _favorites = MutableStateFlow<List<FavoriteProduct>>(emptyList())
    val favorites: StateFlow<List<FavoriteProduct>> = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getAllFavorites()
            Log.d("FavoriteViewModel", "Favorites loaded: ${_favorites.value.size}")
        }
    }

    fun toggleFavorite(product: FavoriteProduct) {
        viewModelScope.launch {
            if (isFavorite(product.productId)) {
                repository.removeFavorite(product)
            } else {
                repository.addFavorite(product)
            }
            loadFavorites()
        }
    }

    suspend fun isFavorite(productId: Int): Boolean {
        return repository.isFavorite(productId)
    }
}
