package com.hyeyeon2371.gaeddal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.hyeyeon2371.gaeddal.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler.postDelayed({this.startActivity(Intent(this@MainActivity, LoginActivity::class.java))},
            2000)
    }
}
