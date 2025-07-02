package com.gundogar.e_commerce_demo.core.util

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView

fun startFlyToCartAnimation(
    sourceImageView: ImageView,
    rootView: ViewGroup,
    targetView: View
) {
    val imageView = ImageView(sourceImageView.context).apply {
        layoutParams = ViewGroup.LayoutParams(sourceImageView.width, sourceImageView.height)
        setImageDrawable(sourceImageView.drawable)
        scaleType = ImageView.ScaleType.CENTER_CROP
    }

    rootView.addView(imageView)

    val startPos = IntArray(2)
    val endPos = IntArray(2)
    sourceImageView.getLocationInWindow(startPos)
    targetView.getLocationInWindow(endPos)

    val startX = startPos[0].toFloat()
    val startY = startPos[1].toFloat()
    val endX = endPos[0].toFloat()
    val endY = endPos[1].toFloat()

    imageView.x = startX
    imageView.y = startY

    val animX = ObjectAnimator.ofFloat(imageView, View.X, startX, endX)
    val animY = ObjectAnimator.ofFloat(imageView, View.Y, startY, endY)

    val scaleX = ObjectAnimator.ofFloat(imageView, View.SCALE_X, 1f, 0.3f)
    val scaleY = ObjectAnimator.ofFloat(imageView, View.SCALE_Y, 1f, 0.3f)

    AnimatorSet().apply {
        playTogether(animX, animY, scaleX, scaleY)
        duration = 600
        interpolator = AccelerateInterpolator()
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                rootView.removeView(imageView)
            }
        })
        start()
    }
}
