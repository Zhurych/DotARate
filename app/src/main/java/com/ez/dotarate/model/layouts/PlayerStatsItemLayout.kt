package com.ez.dotarate.model.layouts

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.Nullable
import com.ez.dotarate.R
import com.ez.dotarate.ViewUtils
import com.ez.dotarate.constants.HIDE_NONE

class PlayerStatsItemLayout : LinearLayout {
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
        background = ViewUtils.generateBackgroundWithShadow(
            this,
            R.color.whiteLight,
            R.dimen.playerStatsScreenRadiusCorner,
            R.color.colorShadow,
            R.dimen.playerStatsScreenElevation,
            Gravity.CENTER,
            HIDE_NONE
        )
    }
}