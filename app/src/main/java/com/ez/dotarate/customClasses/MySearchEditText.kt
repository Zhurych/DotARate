package com.ez.dotarate.customClasses

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.KeyEvent
import android.widget.EditText

class MySearchEditText : EditText {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context) : super(context)

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
        if (event!!.keyCode == KeyEvent.KEYCODE_BACK){
            Log.d("MyLogs", "СКРЫТИЕ КЛАВИАТУРЫ")
            this.clearFocus()
        }
        return super.dispatchKeyEvent(event)
        //return super.onKeyPreIme(keyCode, event)
    }
}