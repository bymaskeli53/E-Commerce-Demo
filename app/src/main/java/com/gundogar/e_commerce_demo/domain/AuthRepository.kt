package com.gundogar.e_commerce_demo.domain

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>
    fun getCurrentUser(): FirebaseUser?
    fun getCurrentUserEmail(): String? // 🔸 Eklenecek fonksiyon



}