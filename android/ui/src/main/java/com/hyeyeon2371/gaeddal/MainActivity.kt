package com.hyeyeon2371.gaeddal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.hyeyeon2371.gaeddal.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getFcmToken()
    }

    private fun getFcmToken(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if(!task.isSuccessful){
                    return@addOnCompleteListener
                }

                val token = task.result?.token
                Log.e("FCM TOKEN", token)

                redirectLoginActivity()
            }
    }

    private fun redirectLoginActivity(){
        handler.postDelayed({this.startActivity(Intent(this@MainActivity, LoginActivity::class.java))},
            2000)
    }

}
