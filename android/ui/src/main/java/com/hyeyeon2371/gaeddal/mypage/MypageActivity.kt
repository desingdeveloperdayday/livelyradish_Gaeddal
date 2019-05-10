package com.hyeyeon2371.gaeddal.mypage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.common.util.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.databinding.ActivityMypageBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MypageActivity : AppCompatActivity(), BaseActivityNavigator {
    private val viewModel : MypageViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    private fun initDataBinding(){
        val binding = DataBindingUtil.setContentView<ActivityMypageBinding>(this, R.layout.activity_mypage)
        binding.name = SharedPrefersUtil.getValue<User>(SharedPrefersUtil.SESSION_DATA, "loggedInUser")?.name?:""
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }


    override fun finishActivity() {
        this.finish()
    }
}