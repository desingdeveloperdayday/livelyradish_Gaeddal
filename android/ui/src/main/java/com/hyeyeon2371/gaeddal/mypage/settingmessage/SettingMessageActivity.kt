package com.hyeyeon2371.gaeddal.mypage.settingmessage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivitySettingmessageBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SettingMessageActivity : AppCompatActivity(), BaseActivityNavigator {
    private lateinit var binding: ActivitySettingmessageBinding
    private val viewModel: SettingMessageViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settingmessage)
        binding.rvSettingmessageList.adapter = SettingMessageAdapter()
        binding.rvSettingmessageList.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun finishActivity() {
        finish()
    }
}