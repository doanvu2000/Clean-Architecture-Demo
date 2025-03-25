package com.wezen.cleanarchitecturedemo.core.utils.ex

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wezen.cleanarchitecturedemo.R

fun View.hide() {
    if (!isInvisible) {
        this.visibility = View.INVISIBLE
    }
}

fun View.show() {
    if (!isVisible) {
        this.visibility = View.VISIBLE
    }
}

fun View.gone() {
    if (!isGone) {
        this.visibility = View.GONE
    }
}

fun View.showOrGone(isShow: Boolean) {
    if (isShow) {
        show()
    } else {
        gone()
    }
}

fun View.showOrHide(isShow: Boolean) {
    if (isShow) {
        show()
    } else {
        hide()
    }
}

fun clickAnimation(mContext: Context?, view: View) {
    //  return new AlphaAnimation(1F, 0.4F); // Change "0.4F" as per your recruitment.
    if (mContext != null) {
        val myAnim = AnimationUtils.loadAnimation(mContext, R.anim.bounce)
        view.startAnimation(myAnim)
    }
}

fun View.clickAnimation() {
    try {
        if (!isAttachedToWindow) {
            return
        }
        context ?: return
        clickAnimation(context, this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun RecyclerView.setGridManager(
    mContext: Context,
    lin: Int,
    holderAdapter: RecyclerView.Adapter<*>,
    orientation: Int = RecyclerView.VERTICAL,
) {
    val manager = GridLayoutManager(mContext, lin, orientation, false)
    this.apply {
        layoutManager = manager
        adapter = holderAdapter
    }
}

fun RecyclerView.setLinearLayoutManagerHorizontal(
    context: Context,
    holderAdapter: RecyclerView.Adapter<*>
) {
    setLinearLayoutManager(context, holderAdapter, RecyclerView.HORIZONTAL)
}

fun RecyclerView.setLinearLayoutManager(
    context: Context,
    holderAdapter: RecyclerView.Adapter<*>,
    orientation: Int = RecyclerView.VERTICAL
) {
    val manager = LinearLayoutManager(context, orientation, false)
    this.apply {
        layoutManager = manager
        adapter = holderAdapter
    }
}
