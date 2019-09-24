package com.ez.dotarate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : ViewModel, VB : ViewDataBinding> : AppCompatActivity() {

    protected var vm: VM? = null
    protected var vb: VB? = null

    @LayoutRes
    protected abstract fun layout(): Int

    protected abstract fun afterCreate(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = DataBindingUtil.setContentView(this, layout())

        try {
            vm = ViewModelProviders.of(this).get(vmTypeClass)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

        afterCreate(savedInstanceState)
    }

    private val vmTypeClass: Class<VM>
        get() {
            try {
                val className =
                    (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0].toString()
                val clazz = Class.forName(className.replace("class ", ""))
                return clazz as Class<VM>
            } catch (e: Exception) {
                throw IllegalStateException(e.message)
            }
        }
}
