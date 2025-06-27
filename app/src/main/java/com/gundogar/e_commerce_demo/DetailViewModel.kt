package com.gundogar.e_commerce_demo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productService: ProductService) :
    ViewModel() {

    suspend fun addToBasket(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String
    ) {
        val basketResponse = productService.getBasketItems()
        val existingProduct = basketResponse.basketProducts?.find {
            it.name == ad
        }

        if (existingProduct == null) {
            productService.addToBasket(
                ad,
                resim,
                kategori,
                fiyat,
                marka,
                siparisAdeti,
                kullaniciAdi

            )
        }
    }

}