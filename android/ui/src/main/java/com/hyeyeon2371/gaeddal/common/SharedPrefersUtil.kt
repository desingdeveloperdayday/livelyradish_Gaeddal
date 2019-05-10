package com.hyeyeon2371.gaeddal.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.hyeyeon2371.gaeddal.App

object SharedPrefersUtil {
    var sharedPrefers: SharedPreferences? = null
    const val SESSION_DATA = "sessionData"
    const val LOGGED_IN_USER = "loggedInUser"
    const val IS_LOGGED_IN = "isLoggedIn"

    inline fun <reified T> getValue(sharedPreferenceName: String, valueName: String): T? {
        sharedPrefers =
            App.globalApplication.applicationContext.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE)

        val json: String? = sharedPrefers!!.getString(valueName, "")

        return if (json == null) {
            null
        } else {
            Gson().fromJson<T>(json.toString(), T::class.java)
        }
    }

    fun saveValue(sharedPreferenceName: String, valueName: String, value: Any) {
        sharedPrefers =
            App.globalApplication.applicationContext.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE)

        val editor = sharedPrefers!!.edit()
        val json = Gson().toJson(value)
        editor.putString(valueName, json)
        editor.apply()
    }

    fun removeValue(sharedPreferenceName: String, valueName: String) {
        sharedPrefers =
            App.globalApplication.applicationContext.getSharedPreferences(sharedPreferenceName, Context.MODE_PRIVATE)

        val editor = sharedPrefers!!.edit()
        editor.remove(valueName)
        editor.apply()
    }
}