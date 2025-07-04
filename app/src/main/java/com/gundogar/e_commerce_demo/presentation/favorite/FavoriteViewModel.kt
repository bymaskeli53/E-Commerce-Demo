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

    init {
        loadFavorites()
    }

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.value = repository.getAllFavorites()
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

    fun isFavorite(productId: Int): Boolean {
        return _favorites.value.any { it.productId == productId }
    }
}
