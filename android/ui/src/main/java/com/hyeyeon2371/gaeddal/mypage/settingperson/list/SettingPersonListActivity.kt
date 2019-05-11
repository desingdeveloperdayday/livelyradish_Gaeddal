package com.hyeyeon2371.gaeddal.mypage.settingperson.list

import android.support.v7.widget.LinearLayoutManager
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseActivity
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivitySettingpersonlistBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SettingPersonListActivity :
    BaseActivity<ActivitySettingpersonlistBinding, SettingPersonListViewModel, SettingPersonListActivityNavigator>(),
    SettingPersonListActivityNavigator {
    private val vm: SettingPersonListViewModel by viewModel { parametersOf(this) }
    override fun injectViewModel(): SettingPersonListViewModel = vm

    override fun getLayout(): Int = R.layout.activity_settingpersonlist

    override fun initView() {
        binding.viewModel = viewModel
        binding.iclSettingpersonlistToolbar.viewModel = viewModel
        binding.rvSettingpersonlist.adapter = SettingPersonListAdapter().apply { viewModel = vm }
        binding.rvSettingpersonlist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun finishActivity() {
        finish()
    }

    override fun refreshList() {
        (binding.rvSettingpersonlist.adapter as SettingPersonListAdapter).notifyItemRemoved(viewModel.selectedItemPos)
    }
}

interface SettingPersonListActivityNavigator : BaseActivityNavigator {
    fun refreshList()
}