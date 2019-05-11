package com.hyeyeon2371.gaeddal.mypage.settingperson.base

import android.content.Intent
import android.view.View
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseActivity
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivitySettingpersonBinding
import com.hyeyeon2371.gaeddal.mypage.settingperson.list.SettingPersonListActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SettingPersonActivity :
    BaseActivity<ActivitySettingpersonBinding, SettingPersonViewModel, SettingPersonActivityNavigator>(),
    SettingPersonActivityNavigator {
    private val vm: SettingPersonViewModel by viewModel { parametersOf(this) }
    override fun getLayout(): Int = R.layout.activity_settingperson
    override fun injectViewModel(): SettingPersonViewModel = vm

    override fun initView() {
        binding.viewModel = viewModel
        binding.iclSettingpersonToolbar.onClickBack = View.OnClickListener { finish() }
        binding.iclSettingpersonToolbar.title = "사람 관리"
    }

    override fun finishActivity() {
        finish()
    }

    override fun redirectPersonListActivity() {
        startActivity(Intent(this, SettingPersonListActivity::class.java))
    }

    override fun redirectAddPersonActivity() {

    }

    override fun redirectRequestPersonActivity() {

    }
}

interface SettingPersonActivityNavigator : BaseActivityNavigator {
    fun redirectPersonListActivity()
    fun redirectAddPersonActivity()
    fun redirectRequestPersonActivity()
}