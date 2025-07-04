package com.gundogar.e_commerce_demo.data.remote

import com.gundogar.e_commerce_demo.data.remote.model.ApiResponse
import com.gundogar.e_commerce_demo.data.remote.model.BasketResponse
import com.gundogar.e_commerce_demo.data.remote.model.ProductResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductService {


    @GET("tumUrunleriGetir.php")
    suspend fun getAllProducts(): ProductResponse

    @FormUrlEncoded
    @POST("sepeteUrunEkle.php")
    suspend fun addToBasket(
        @Field("ad") ad: String,
        @Field("resim") resim: String,
        @Field("kategori") kategori: String,
        @Field("fiyat") fiyat: Int,
        @Field("marka") marka: String,
        @Field("siparisAdeti") siparisAdeti: Int,
        @Field("kullaniciAdi") kullaniciAdi: String
    ): ApiResponse

    @FormUrlEncoded
    @POST("sepettekiUrunleriGetir.php")
    suspend fun getBasketItems(
        @Field("kullaniciAdi") kullaniciAdi: String
    ): BasketResponse

    @FormUrlEncoded
    @POST("sepettenUrunSil.php")
    suspend fun deleteFromBasket(
        @Field("sepetId") sepetId: Int,
        @Field("kullaniciAdi") userName: String
    ): ApiResponse
}