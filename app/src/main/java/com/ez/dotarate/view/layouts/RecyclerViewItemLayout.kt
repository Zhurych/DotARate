package com.ez.dotarate.view.layouts

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.Nullable
import com.ez.dotarate.R
import com.ez.dotarate.model.ViewUtils.generateBackgroundWithShadow


class RecyclerViewItemLayout : LinearLayout {
    constructor(context: Context) : super(context) {
        initBackground()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs) {
        initBackground()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initBackground()
    }

    private fun initBackground() {
        background = generateBackgroundWithShadow(
            this, R.color.white_tran,
            R.dimen.radius_corner, R.color.colorShadow, R.dimen.elevation, Gravity.CENTER
        )
    }
}