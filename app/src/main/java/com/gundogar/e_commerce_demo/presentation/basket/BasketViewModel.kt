package com.gundogar.e_commerce_demo.presentation.basket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.gundogar.e_commerce_demo.data.remote.ApiResult
import com.gundogar.e_commerce_demo.data.remote.model.BasketProduct
import com.gundogar.e_commerce_demo.data.remote.ProductService
import com.gundogar.e_commerce_demo.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(private val productService: ProductService,private val authRepository: AuthRepository) :
    ViewModel() {

    private val _basketItems = MutableStateFlow<ApiResult<List<BasketProduct>>>(ApiResult.Loading)
    val basketItems: StateFlow<ApiResult<List<BasketProduct>>> = _basketItems

    init {
        getBasketItems()
    }

    fun getBasketItems() {
        viewModelScope.launch {
            _basketItems.value = ApiResult.Loading
            try {
                val response = productService.getBasketItems(authRepository.getCurrentUserEmail() ?: "muhammet_gundogar")
                _basketItems.value = ApiResult.Success(response.basketProducts)
            } catch (e: Exception) {
                _basketItems.value = ApiResult.Error(
                    message = e.localizedMessage ?: "Hata oluştu",
                    exception = e
                )
            }
        }
    }


    fun deleteBasketItems(productId: Int) {
        viewModelScope.launch {
            productService.deleteFromBasket(productId,authRepository.getCurrentUserEmail()?: "muhammet_gundogar")
            getBasketItems()
        }
    }
}