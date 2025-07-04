package com.gundogar.e_commerce_demo.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundogar.e_commerce_demo.data.remote.ApiResult
import com.gundogar.e_commerce_demo.data.remote.model.ProductResponse
import com.gundogar.e_commerce_demo.data.remote.ProductService
import com.gundogar.e_commerce_demo.core.util.safeApiCall
import com.gundogar.e_commerce_demo.data.remote.model.Product
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

    private var allProducts: List<Product> = emptyList()

    private val _filteredProducts = MutableStateFlow<List<Product>>(emptyList())
    val filteredProducts: StateFlow<List<Product>> = _filteredProducts.asStateFlow()

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

            if (result is ApiResult.Success) {
                allProducts = result.data.products
                _filteredProducts.value = allProducts
            }
        }
    }

    fun filterProducts(query: String) {
        _filteredProducts.value = if (query.isBlank()) {
            allProducts
        } else {
            allProducts.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
    }
}
