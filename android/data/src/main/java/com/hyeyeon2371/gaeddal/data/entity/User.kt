package com.hyeyeon2371.gaeddal.data.entity

import java.util.*


data class User(var mId: String? = "", var email: String? = "", var name: String? = "") : Observable(){
    var isTimeReserved : Boolean = false
        get() {
            // TEMP
            return if (!mId.isNullOrBlank()) {
                lastReservedTime = "22:00"
                true
            } else {
                false
            }
        }
        set(value) {
            field = value
            setChanged()
            notifyObservers("isTimeReserved")
        }

    var lastReservedTime: String = ""
        get() {
            return if (isTimeReserved) {
                field
            } else {
                "00:00"
            }
        }
        set(value) {
            field = value
            setChanged()
            notifyObservers("lastReservedTime")
        }

    var notificationCount : Int = 77

    var profileUrl = ""

}

