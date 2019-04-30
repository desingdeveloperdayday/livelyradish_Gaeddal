package com.hyeyeon2371.gaeddal.login

import android.annotation.SuppressLint
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.ResultCodeFlag
import com.hyeyeon2371.gaeddal.databinding.ActivityLoginBinding
import io.reactivex.Observable
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel : LoginViewModel by viewModel()
    private lateinit var binding : ActivityLoginBinding
    private lateinit var googleSignInClient : GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build().let {
                googleSignInClient =  GoogleSignIn.getClient(this, it)
            }

        initDataBinding()
    }

    @SuppressLint("CheckResult")
    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        Observable.create<View> {
            binding.btnLoginGoogle.setOnClickListener(it::onNext)
        }.subscribe {
            startActivityForResult(googleSignInClient.signInIntent, ResultCodeFlag.GOOGLE_LOGIN.value)
        }

        binding.executePendingBindings()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ResultCodeFlag.GOOGLE_LOGIN.value){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                task.getResult(ApiException::class.java).let{
                    val mId = it?.id
                    val email = it?.email
                    val name = it?.displayName
                }
            }catch (e: ApiException){
                Log.e("failed", e.message)
            }
        }
    }
}