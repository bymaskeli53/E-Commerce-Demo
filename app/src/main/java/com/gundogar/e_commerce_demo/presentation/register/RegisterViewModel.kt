package com.gundogar.e_commerce_demo.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gundogar.e_commerce_demo.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun register(email: String, password: String) {
        viewModelScope.launch {
            authRepository.register(email, password)

        }
    }




}