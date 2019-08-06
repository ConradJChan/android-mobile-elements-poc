package com.example.mobileelementspoc

import android.content.Context
import android.content.pm.ApplicationInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WebViewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WebViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class WebViewFragment : Fragment() {
    val token: String = "JTJVxTSib8wKXfu2PTkx5AsiTbluOms2"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_web_view, container, false)
        val mWebView = view.findViewById(R.id.webview) as WebView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        mWebView.loadUrl("https://conradjchan.github.io/box-elements-mobile/")
        mWebView.settings.javaScriptEnabled = true
        mWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                val args = arguments
                val fileId = args?.getString("fileId")
                val token = args?.getString("token")
                mWebView.loadUrl("javascript:(function () { window.fetchData('$fileId', '$token'); })()")
//                mWebView.loadUrl("javascript:(function () { console.log('hello'); })()")
            }
        }


        return view
    }


    companion object {
        fun newInstance(fileId: String, token: String): WebViewFragment {
            val fragment = WebViewFragment()
            val args = Bundle()
            args.putString("fileId", fileId)
            args.putString("token", token)
            fragment.arguments = args
            return fragment
        }
    }
}
