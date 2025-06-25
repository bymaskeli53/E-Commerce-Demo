package com.gundogar.e_commerce_demo

import com.gundogar.e_commerce_demo.NetworkConstants.IMAGE_BASE_URL

fun String.toFullImageUrl(): String = IMAGE_BASE_URL + this