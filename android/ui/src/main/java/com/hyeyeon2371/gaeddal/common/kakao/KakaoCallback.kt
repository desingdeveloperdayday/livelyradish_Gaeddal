package com.hyeyeon2371.gaeddal.common.kakao

import android.util.Log
import com.hyeyeon2371.gaeddal.common.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.login.LoginViewModel
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger

class KakaoCallback(private val viewModel: LoginViewModel) : ISessionCallback {
    override fun onSessionOpenFailed(exception: KakaoException?) {
        Logger.e(exception.toString())
    }

    override fun onSessionOpened() {
        UserManagement.getInstance().requestMe(object : MeResponseCallback() {
            override fun onSuccess(result: UserProfile?) {
                Log.e("KAKAO CALLBACK", result.toString())

                val nickName = result?.nickname
                val id = result?.id

                saveUserData(User(mId = id.toString(), email = result?.email, name = nickName))
                viewModel.navigator.finishActivity()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                Logger.e(errorResult.toString())
            }

            override fun onNotSignedUp() {
            }

            override fun onFailure(errorResult: ErrorResult?) {
                Logger.e(errorResult.toString())
            }
        })
    }

    private fun saveUserData(user: User) {
        SharedPrefersUtil.saveValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.IS_LOGGED_IN, true)
        SharedPrefersUtil.saveValue(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER, user)
    }

}