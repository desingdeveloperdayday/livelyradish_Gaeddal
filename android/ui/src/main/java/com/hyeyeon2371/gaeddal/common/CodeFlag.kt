package com.hyeyeon2371.gaeddal.common

enum class RequestCodeFlag(val flag: Int) {
    KAKAO_LOGIN(10001), GOOGLE_LOGIN(10002), CHANGE_NAME(10003), WRITE_SETTING_MSG(10004), EDIT_SETTING_MSG(10005)
}

enum class ResultCodeFlag(val flag: Int) {
    DELETE_SETTING_MSG(20001), EDIT_SETTING_MSG(20002)
}