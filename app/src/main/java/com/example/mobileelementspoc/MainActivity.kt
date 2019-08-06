package com.example.mobileelementspoc

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity() : AppCompatActivity(), PreviewFragment.SubmitData {

    lateinit var toolbar: ActionBar
    override var fileId: String = "419470392429"
    override var token: String = "dBNvzC1O2Quvudv1Ih33wPstEtOPmcmS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = supportActionBar!!
        val bottomNavigation: BottomNavigationView = findViewById(R.id.nav_view)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        openFragment(PreviewFragment.newInstance(fileId, token))
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_preview -> {
                toolbar.title = "Preview"
                val previewFragment = PreviewFragment.newInstance(fileId, token)
                openFragment(previewFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_webview_activity -> {
                toolbar.title = "Webview Activity"
                val webviewFragment = WebViewFragment.newInstance(fileId, token)
                openFragment(webviewFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
