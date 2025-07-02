package com.gundogar.e_commerce_demo

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gundogar.e_commerce_demo.NetworkConstants.IMAGE_BASE_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun String.toFullImageUrl(): String = IMAGE_BASE_URL + this

// Extension function to set the visibility to VISIBLE
fun View.show() {
    visibility = View.VISIBLE
}

// Extension function to set the visibility to INVISIBLE
fun View.hide() {
    visibility = View.INVISIBLE
}

// Extension function to set the visibility to GONE
fun View.gone() {
    visibility = View.GONE
}

fun Fragment.launchWhenStarted(block: suspend CoroutineScope.() -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}