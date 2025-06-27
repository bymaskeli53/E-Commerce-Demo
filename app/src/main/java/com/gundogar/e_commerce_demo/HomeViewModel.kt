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
class HomeViewModel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {

    private val _products = MutableStateFlow<ApiResult<ProductResponse>>(ApiResult.Loading)
    val products: StateFlow<ApiResult<ProductResponse>> = _products.asStateFlow()

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _products.value = ApiResult.Loading
            val result = safeApiCall {
                productService.getAllProducts()
            }
            _products.value = result
//            val productResponse = apiService.getAllProducts()
//            _products.value = productResponse.products
        }
    }

}