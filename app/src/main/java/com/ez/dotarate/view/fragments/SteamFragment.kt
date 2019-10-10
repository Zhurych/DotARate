package com.ez.dotarate.view.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat
import com.ez.dotarate.R
import com.ez.dotarate.databinding.FragmentSteamBinding
import com.ez.dotarate.view.BaseFragment
import com.ez.dotarate.view.activities.MainActivity
import com.ez.dotarate.viewModel.SteamViewModel


// Убираем warning
@SuppressLint("SetJavaScriptEnabled")
class SteamFragment : BaseFragment<SteamViewModel, FragmentSteamBinding>() {

    val LOG_TAG = "MyLogs"

    val REALM_PARAM = "DotA Rate"

    private val mUrl = "https://steamcommunity.com/openid/login?" +
            "openid.claimed_id=http://specs.openid.net/auth/2.0/identifier_select&" +
            "openid.identity=http://specs.openid.net/auth/2.0/identifier_select&" +
            "openid.mode=checkid_setup&" +
            "openid.ns=http://specs.openid.net/auth/2.0&" +
            "openid.realm=https://" + REALM_PARAM + "&" +
            "openid.return_to=https://" + REALM_PARAM + "/signin/"


    override fun layout() = R.layout.fragment_steam

    override fun afterCreateView(view: View) {

        val window = activity?.window

        val decorView = window?.decorView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            decorView?.systemUiVisibility =
                decorView?.getSystemUiVisibility()!! and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window?.statusBarColor = ContextCompat.getColor(activity!!, R.color.colorBlue)
        }

        // Container(родитель) WebView
        val mCWebViewContainer = vb.wvContainer

        // WebView
        val mWebView = vb.webView
        val settings: WebSettings = mWebView.settings
        settings.javaScriptEnabled = true

        mWebView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                if (url == null)
                    return

                vb.progressBar.visibility = View.GONE

                try {
                    val substr = "openid%2Fid%"
                    val before = url.substring(0, url.indexOf(substr))
                    val after = url.substring(url.indexOf(substr) + substr.length)

                    val steamId = after.substring(2, 19)

                    if (steamId.isNotEmpty()) {
                        // Очищаем WebView
                        // Make sure you remove the WebView from its parent view before doing anything.
                        mCWebViewContainer.removeAllViews()

                        mWebView.clearHistory()

                        // NOTE: clears RAM cache, if you pass true, it will also clear the disk cache.
                        // Probably not a great idea to pass true if you have other WebViews still alive.
                        mWebView.clearCache(true)

                        // Loading a blank page is optional, but will ensure that the WebView isn't doing anything when you destroy it.
                        mWebView.loadUrl("about:blank")

                        mWebView.onPause()
                        mWebView.removeAllViews()
                        @Suppress("DEPRECATION")
                        mWebView.destroyDrawingCache()

                        // NOTE: This pauses JavaScript execution for ALL WebViews,
                        // do not use if you have other WebViews still alive.
                        // If you create another WebView after calling this,
                        // make sure to call mWebView.resumeTimers().
                        mWebView.pauseTimers()

                        // NOTE: This can occasionally cause a segfault below API 17 (4.2)
                        mWebView.destroy()

                        // Save User ID
                        vm.saveId(steamId.toLong())

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                            window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                            window?.statusBarColor =
                                ContextCompat.getColor(activity!!, R.color.colorPrimaryDark)
                        }

                        val intent = Intent(activity, MainActivity::class.java)
                        intent.putExtra("id", steamId.toLong())
                        startActivity(intent)

                        activity!!.finish()

                    }
                } catch (ex: StringIndexOutOfBoundsException) {
                    return
                } catch (ex: NumberFormatException) {
                    Log.d(LOG_TAG, "Ошибка при преобразовании String (ID) в Long")
                }
            }
        }

        mWebView.loadUrl(mUrl)
    }
}