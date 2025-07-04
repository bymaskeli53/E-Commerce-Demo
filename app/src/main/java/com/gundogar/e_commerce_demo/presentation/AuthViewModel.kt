package com.gundogar.e_commerce_demo.presentation

import androidx.lifecycle.ViewModel
import com.gundogar.e_commerce_demo.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _isUserLoggedIn = MutableStateFlow<Boolean?>(null)
    val isUserLoggedIn: StateFlow<Boolean?> = _isUserLoggedIn

    fun checkUserLoggedIn() {
        val user = authRepository.getCurrentUser()
        _isUserLoggedIn.value = user != null
    }
}