package com.hyeyeon2371.gaeddal.main

import android.databinding.Bindable
import android.databinding.ObservableInt
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.hyeyeon2371.gaeddal.common.BaseObservableViewModel
import com.hyeyeon2371.gaeddal.common.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.data.entity.User
import java.util.*

class MainViewModel(private val navigator: MainActivityNavigator) : BaseObservableViewModel(), Observer {
    val toolbarTitle = "나늦어"

    var loggedInUser =
        SharedPrefersUtil.getValue<User>(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER)
        @Bindable
        get() = field

    var loggedIn =
        SharedPrefersUtil.getValue<Boolean>(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.IS_LOGGED_IN) ?: false
        @Bindable
        get() = field

    var notificationEmptyMsgVisibility: ObservableInt = ObservableInt(View.GONE)
        @Bindable
        get() {
            return if (loggedIn) {
                if (loggedInUser?.notificationCount ?: 0 == 0) {
                    ObservableInt(View.VISIBLE)
                } else {
                    ObservableInt(View.GONE)
                }
            } else {
                ObservableInt(View.GONE)
            }
        }

    var notificationCountMsgVisibility: ObservableInt = ObservableInt(View.GONE)
        @Bindable
        get() {
            return if (loggedIn) {
                if (loggedInUser?.notificationCount ?: 0 > 0) {
                    ObservableInt(View.VISIBLE)
                } else {
                    ObservableInt(View.GONE)
                }
            } else {
                ObservableInt(View.GONE)
            }
        }

    init {
        loggedInUser?.addObserver(this)
    }

    fun onClickMypage() = navigator.redirectActivity()

    fun getIsLoggedIn() {
        loggedIn =
            SharedPrefersUtil.getValue<Boolean>(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.IS_LOGGED_IN) ?: false
        loggedInUser =
            SharedPrefersUtil.getValue<User>(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER)
        notifyPropertyChanged(BR.loggedIn)
        notifyPropertyChanged(BR.loggedInUser)
        notifyPropertyChanged(BR.notificationEmptyMsgVisibility)
        notifyPropertyChanged(BR.notificationCountMsgVisibility)

    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg is String) {
            when (arg) {
                "isTimeReserved" -> {
                    notifyPropertyChanged(BR.loggedInUser)
                }
                "lastReservedTime" -> {
                    notifyPropertyChanged(BR.loggedInUser)
                }
            }
        }
    }
}