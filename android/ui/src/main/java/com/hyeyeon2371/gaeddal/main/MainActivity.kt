package com.hyeyeon2371.gaeddal.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.google.firebase.iid.FirebaseInstanceId
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.databinding.ActivityMainBinding
import com.hyeyeon2371.gaeddal.login.LoginActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getFcmToken()
        initDataBinding()
    }

    private fun getFcmToken(){
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if(!task.isSuccessful){
                    return@addOnCompleteListener
                }

                val token = task.result?.token
                Log.e("FCM TOKEN", token)

                // redirectLoginActivity()
            }
    }

    private fun redirectLoginActivity(){
        handler.postDelayed({this.startActivity(Intent(this@MainActivity, LoginActivity::class.java))},
            2000)
    }

    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.iclMainToolbar.title = "나 늦어"
        binding.iclMainToolbar.onClickMypage = View.OnClickListener { }
        binding.executePendingBindings()
    }

}
