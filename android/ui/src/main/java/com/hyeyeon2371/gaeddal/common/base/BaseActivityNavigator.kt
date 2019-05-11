package com.hyeyeon2371.gaeddal.common.base

import android.widget.Toast
import com.hyeyeon2371.gaeddal.App

interface BaseActivityNavigator {
     // fun redirectActivity()
     fun finishActivity()

     fun showMessage(message: String) {
          Toast.makeText(App.globalApplication.applicationContext, message, Toast.LENGTH_SHORT).show()
     }
}