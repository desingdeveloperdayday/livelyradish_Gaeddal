package com.hyeyeon2371.gaeddal.common.kakao

import com.hyeyeon2371.gaeddal.App
import com.kakao.auth.*

class KakaoSDKAdpater(private val globalApplication: App) : KakaoAdapter() {
    override fun getApplicationConfig(): IApplicationConfig {
        return IApplicationConfig { globalApplication }
    }

    override fun getSessionConfig(): ISessionConfig {
        return object : ISessionConfig {
            override fun isSaveFormData(): Boolean {
                return true
            }

            override fun getAuthTypes(): Array<AuthType> {
                return arrayOf(AuthType.KAKAO_LOGIN_ALL)
            }

            override fun isSecureMode(): Boolean {
                return false
            }

            override fun getApprovalType(): ApprovalType {
                return ApprovalType.INDIVIDUAL
            }

            override fun isUsingWebviewTimer(): Boolean {
                return false
            }
        }
    }
}