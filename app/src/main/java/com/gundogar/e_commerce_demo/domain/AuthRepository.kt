package com.gundogar.e_commerce_demo.domain

interface AuthRepository {

    suspend fun register(email: String, password: String): Result<Unit>
    suspend fun login(email: String, password: String): Result<Unit>


}