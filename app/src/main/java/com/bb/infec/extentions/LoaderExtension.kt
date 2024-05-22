package com.bb.infec.extentions

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import com.bb.infec.R
import java.lang.Exception


@SuppressLint("InflateParams")
fun AppCompatActivity.showLoader() {
    try {
        val contentView = findViewById<ViewGroup>(android.R.id.content)
        if (contentView.childCount > 0) {
            val rootView = contentView.getChildAt(0) as ViewGroup
            if (rootView is ConstraintLayout) {
                val set = ConstraintSet()
                val childView = LayoutInflater.from(this).inflate(R.layout.loader_layout, null)
                childView.id = View.generateViewId()

                rootView.addView(childView, rootView.childCount)
                if (rootView.id < 0)
                    rootView.id = View.generateViewId()
                set.clone(rootView)
                set.connect(
                    childView.id,
                    ConstraintSet.TOP,
                    rootView.id,
                    ConstraintSet.TOP,
                    0
                )
                set.constrainHeight(childView.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
                set.constrainWidth(childView.id, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
                set.connect(
                    childView.id,
                    ConstraintSet.LEFT,
                    rootView.id,
                    ConstraintSet.LEFT,
                    0
                )
                set.connect(
                    childView.id,
                    ConstraintSet.RIGHT,
                    rootView.id,
                    ConstraintSet.RIGHT,
                    0
                )
                set.connect(
                    childView.id,
                    ConstraintSet.BOTTOM,
                    rootView.id,
                    ConstraintSet.BOTTOM,
                    0
                )
                set.applyTo(rootView)
            }

        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

fun AppCompatActivity.hideLoader() {
    try {
        val contentView = findViewById<ViewGroup>(android.R.id.content)
        if (contentView.childCount > 0) {
            val rootView = contentView.getChildAt(0) as ViewGroup
            if (rootView.childCount > 0) {
                if (rootView.getChildAt(rootView.childCount - 1) is ConstraintLayout) {
                    if ((rootView.getChildAt(rootView.childCount - 1) as ConstraintLayout).getChildAt(
                            0
                        ) is CardView
                    ) {
                        rootView.removeViewAt(rootView.childCount - 1)
                    }
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

}

@SuppressLint("InflateParams")
fun Fragment.showLoader() {
    try {
        (requireActivity() as AppCompatActivity).showLoader()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.hideLoader() {
    try {
        (requireActivity() as AppCompatActivity).hideLoader()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun AppCompatActivity.toggleLoader(flag: Boolean) {
    try {
        if (flag) {
            showLoader()
        } else {
            hideLoader()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Fragment.toggleLoader(flag: Boolean) {
    try {
        if (flag) {
            (requireActivity() as AppCompatActivity).showLoader()
        } else {
            (requireActivity() as AppCompatActivity).hideLoader()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}