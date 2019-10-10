package com.ez.dotarate.adapters

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ez.dotarate.R
import com.squareup.picasso.Picasso

object BindingAdapter {

    @BindingAdapter("url", "errorImage")
    @JvmStatic
    fun loadImage(view: ImageView, url: String?, errorImage: Drawable) {
        if (TextUtils.isEmpty(url))
            view.setImageResource(R.drawable.ic_empty_avatar)
        else
            Picasso.get().load(url).placeholder(errorImage).error(
                errorImage
            ).into(view)
    }
}