package com.hyeyeon2371.gaeddal.mypage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.databinding.ActivityMypageBinding

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<ActivityMypageBinding>(this, R.layout.activity_mypage)
        binding.name = SharedPrefersUtil.getValue<User>(SharedPrefersUtil.SESSION_DATA, "loggedInUser")?.name?:""
        binding.executePendingBindings()
    }

}