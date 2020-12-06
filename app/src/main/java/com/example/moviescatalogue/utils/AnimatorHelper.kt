package com.example.moviescatalogue.utils

import android.animation.*
import android.view.View

object AnimatorHelper {
    private const val repeat = 1
    private const val rotateDuration = 1250
    private const val motionDuration = 850
    private const val scaleDuration = 750

    private fun ObjectAnimator.disableViewDuringAnimation(view: View) {
        addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                view.isEnabled = true
            }

            override fun onAnimationStart(animation: Animator?, isReverse: Boolean) {
                view.isEnabled = false
            }
        })
    }

    fun startAnimator(view: View){
        val setAnim = AnimatorSet().apply {
            rotate(view)
            motion(view)
            scale(view)
        }
        AnimatorSet().play(setAnim)
    }

    private fun rotate(view: View) {
        val rotateAnimator = ObjectAnimator.ofFloat(view, View.ROTATION, -360f, 0f)
        rotateAnimator.duration = rotateDuration.toLong()
        rotateAnimator.disableViewDuringAnimation(view)
        rotateAnimator.start()
    }

    private fun motion(view: View) {
        val motionX = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, -385f)
        motionX.duration = motionDuration.toLong()
        motionX.repeatCount = repeat
        motionX.repeatMode = ObjectAnimator.REVERSE
        motionX.start()
        val motionY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, -850f)
        motionY.repeatCount = repeat
        motionY.repeatMode = ObjectAnimator.REVERSE
        motionY.start()
    }

    private fun scale(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 5f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 5f)
        val scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
        scaleAnimator.duration = scaleDuration.toLong()
        scaleAnimator.repeatCount = repeat
        scaleAnimator.repeatMode = ObjectAnimator.REVERSE
        scaleAnimator.start()
    }
}