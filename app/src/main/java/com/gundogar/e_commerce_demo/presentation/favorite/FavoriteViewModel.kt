package com.gundogar.e_commerce_demo.presentation.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundogar.e_commerce_demo.data.local.model.FavoriteProduct
import com.gundogar.e_commerce_demo.domain.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {

    private val _favorites = MutableStateFlow<List<FavoriteProduct>>(emptyList())
    val favorites: StateFlow<List<FavoriteProduct>> = _favorites

    // Fragment açıldığında favorileri yükle
    init {
        loadFavorites()
    }

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
                Log.d("FavoriteViewModel", "Removed from favorites: ${product.productId}")
            } else {
                repository.addFavorite(product)
                Log.d("FavoriteViewModel", "Added to favorites: ${product.productId}")
            }
            loadFavorites() // Listeyi güncelle
        }
    }

    // Suspend fonksiyonu kaldırdık, StateFlow'dan kontrol ediyoruz
    fun isFavorite(productId: Int): Boolean {
        return _favorites.value.any { it.productId == productId }
    }
}
