package com.gundogar.e_commerce_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val productService: ProductService) :
    ViewModel() {
    private val _basketItems = MutableStateFlow<List<BasketProduct>>(emptyList())
    val basketItems: StateFlow<List<BasketProduct>> = _basketItems.asStateFlow()

    init {
        getBasketItems()
    }

    fun getBasketItems() {
        viewModelScope.launch {
            val productResponse = productService.getBasketItems("muhammet_gundogar")
            _basketItems.value = productResponse.basketProducts
        }
    }


    fun deleteBasketItems(productId: Int) {
        viewModelScope.launch {
            productService.deleteFromBasket(productId)
            getBasketItems()
        }
    }
}