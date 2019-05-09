package com.hyeyeon2371.gaeddal.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.CallActivityNavigator
import com.hyeyeon2371.gaeddal.common.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.databinding.ActivityMainBinding
import com.hyeyeon2371.gaeddal.login.LoginActivity
import com.hyeyeon2371.gaeddal.mypage.MypageActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

open class MainActivity : AppCompatActivity(), CallActivityNavigator {
    private val viewModel: MainViewModel by viewModel {
        parametersOf(this)
    }

    private lateinit var binding: ActivityMainBinding
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getFcmToken()
        initDataBinding()
    }

    private fun getFcmToken() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@addOnCompleteListener
                }

                val token = task.result?.token
                Log.e("FCM TOKEN", token)

                // redirectLoginActivity()
            }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.iclMainToolbar.viewModel = viewModel
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun redirectActivity() {
        val isLoggedIn = SharedPrefersUtil.getValue<Boolean>(SharedPrefersUtil.SESSION_DATA, "isLoggedIn") ?: false
        if (isLoggedIn) {
            this.startActivity(Intent(this, MypageActivity::class.java))
        } else {
            this.startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun redirectLoginActivity() {
        handler.postDelayed(
            { this.startActivity(Intent(this@MainActivity, LoginActivity::class.java)) },
            2000
        )
    }
}
