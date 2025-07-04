package com.gundogar.e_commerce_demo.presentation.detail

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.gundogar.e_commerce_demo.data.remote.ProductService
import com.gundogar.e_commerce_demo.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okio.EOFException
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val productService: ProductService,private val authRepository: AuthRepository) :
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
            val basketResponse = productService.getBasketItems(authRepository.getCurrentUserEmail()?: "muhammet_gundogar")
            val existingProduct = basketResponse.basketProducts.find {
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