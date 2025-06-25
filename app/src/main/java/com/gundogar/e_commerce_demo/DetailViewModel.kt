package com.gundogar.e_commerce_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val prodcutService: ProductService) :
    ViewModel() {



    fun addToBasket(
        ad: String,
        resim: String,
        kategori: String,
        fiyat: Int,
        marka: String,
        siparisAdeti: Int,
        kullaniciAdi: String
    ) {
        viewModelScope.launch {
            prodcutService.addToBasket(
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