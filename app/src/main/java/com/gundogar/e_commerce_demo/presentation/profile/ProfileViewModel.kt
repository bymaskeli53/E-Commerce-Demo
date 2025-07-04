package com.gundogar.e_commerce_demo.presentation.profile

import androidx.lifecycle.ViewModel
import com.gundogar.e_commerce_demo.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _email = MutableStateFlow<String?>(null)
    val email: StateFlow<String?> = _email

    fun loadUserEmail() {
        _email.value = authRepository.getCurrentUserEmail()
    }

    fun signOut() {
        authRepository.signOut()
    }
}