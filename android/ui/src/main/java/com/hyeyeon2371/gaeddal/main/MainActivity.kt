package com.hyeyeon2371.gaeddal.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.util.TimerUtil
import com.hyeyeon2371.gaeddal.databinding.ActivityMainBinding
import com.hyeyeon2371.gaeddal.login.LoginActivity
import com.hyeyeon2371.gaeddal.mypage.MypageActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import  android.arch.lifecycle.Observer
import com.android.databinding.library.baseAdapters.BR

open class MainActivity : AppCompatActivity(), MainActivityNavigator {
    private val viewModel: MainViewModel by viewModel {
        parametersOf(this)
    }

    private lateinit var binding: ActivityMainBinding
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getFcmToken()
        initDataBinding()

        startTimer("2019-05-11 01:00:00")
    }

    override fun onResume() {
        super.onResume()
        viewModel.getIsLoggedIn()
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
        if (viewModel.loggedIn) {
            this.startActivity(Intent(this, MypageActivity::class.java))
        } else {
            this.startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    override fun finishActivity() {
        this@MainActivity.finish()
    }

    private fun startTimer(reservedDateTime: String) {
        val timerUtil = TimerUtil(reservedDateTime)
        val minuteObserver = Observer<String> {
            viewModel.timerMinutes = ObservableField(it)
            viewModel.notifyPropertyChanged(BR.remainingTime)
        }

        val hourObserver = Observer<String> {
            viewModel.timerHours = if (it?.length ?: 0 > 1) {
                ObservableField(it)
            } else {
                ObservableField("0$it")
            }
            viewModel.notifyPropertyChanged(BR.remainingTime)
        }

        timerUtil.addObservers(this, hourObserver, minuteObserver)
        timerUtil.startTimer()
    }

    private fun redirectLoginActivity() {
        handler.postDelayed(
            { this.startActivity(Intent(this@MainActivity, LoginActivity::class.java)) },
            2000
        )
    }
}
