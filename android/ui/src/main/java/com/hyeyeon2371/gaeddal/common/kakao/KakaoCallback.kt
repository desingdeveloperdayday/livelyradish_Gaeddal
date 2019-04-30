package com.hyeyeon2371.gaeddal.common.kakao

import android.util.Log
import com.hyeyeon2371.gaeddal.login.LoginViewModel
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.log.Logger

class KakaoCallback(val viewModel: LoginViewModel) : ISessionCallback {
    override fun onSessionOpenFailed(exception: KakaoException?) {
        Logger.e(exception.toString())
    }

    override fun onSessionOpened() {
        UserManagement.getInstance().requestMe(object : MeResponseCallback() {
            override fun onSuccess(result: UserProfile?) {
                Log.e("KAKAO CALLBACK", result.toString())

                val nickName = result?.nickname
                val id = result?.id

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

}