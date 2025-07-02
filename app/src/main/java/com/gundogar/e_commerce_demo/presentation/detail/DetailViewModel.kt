package com.gundogar.e_commerce_demo.presentation.detail

import androidx.lifecycle.ViewModel
import com.gundogar.e_commerce_demo.data.remote.ProductService
import dagger.hilt.android.lifecycle.HiltViewModel
import okio.EOFException
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
        try {
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
        } catch (e: EOFException) {
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