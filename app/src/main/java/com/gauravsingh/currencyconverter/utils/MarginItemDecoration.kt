package com.gauravsingh.currencyconverter.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val margin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            parent.getChildAdapterPosition(view).let {

                if (it % 2 == 0) {
                    left = margin
                    right = margin / 2
                } else {
                    left = margin / 2
                    right = margin
                }
            }

            bottom = margin
        }
    }
}