package com.hyeyeon2371.gaeddal.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.common.RequestCodeFlag
import com.hyeyeon2371.gaeddal.common.util.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.common.kakao.KakaoCallback
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.databinding.ActivityLoginBinding
import com.kakao.auth.Session
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import io.reactivex.Observable
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LoginActivity : AppCompatActivity(), BaseActivityNavigator {
    private val viewModel: LoginViewModel by viewModel { parametersOf(this)}
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private var kakaoCallback: KakaoCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail().build().let {
                googleSignInClient = GoogleSignIn.getClient(this, it)
            }

        initDataBinding()
        initKakao()
    }

    override fun onDestroy() {
        logoutKakao()
        Session.getCurrentSession().removeCallback(kakaoCallback)
        super.onDestroy()
    }


    @SuppressLint("CheckResult")
    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.iclLoginToolbar.title = "로그인"
        binding.iclLoginToolbar.onClickBack = View.OnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        binding.viewModel = viewModel

        Observable.create<View> {
            binding.btnLoginGoogle.setOnClickListener(it::onNext)
        }.subscribe {
            startActivityForResult(googleSignInClient.signInIntent, RequestCodeFlag.GOOGLE_LOGIN.flag)
        }

        binding.executePendingBindings()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RequestCodeFlag.GOOGLE_LOGIN.flag -> {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    task.getResult(ApiException::class.java).let {
                        val mId = it?.id
                        val email = it?.email
                        val name = it?.displayName

                        saveUserData(User(mId = mId, email = email, name = name))
                        finish()
                    }
                } catch (e: ApiException) {
                    Log.e("failed", e.message)
                }
            }

            RequestCodeFlag.KAKAO_LOGIN.flag -> {
                if (resultCode == Activity.RESULT_OK &&
                    Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)
                ) {
                    return
                }
            }

        }
    }

    // kakao
    private fun initKakao() {
        kakaoCallback = KakaoCallback(viewModel)
        Session.getCurrentSession().addCallback(kakaoCallback)
    }

    private fun logoutKakao() {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {

            }
        })
    }

    private fun saveUserData(user: User) {
        SharedPrefersUtil.saveValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.IS_LOGGED_IN, true)
        SharedPrefersUtil.saveValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER, user)
    }


    override fun finishActivity() {
        this@LoginActivity.finish()
    }

}