package com.gundogar.e_commerce_demo

import android.view.View
import com.gundogar.e_commerce_demo.NetworkConstants.IMAGE_BASE_URL

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