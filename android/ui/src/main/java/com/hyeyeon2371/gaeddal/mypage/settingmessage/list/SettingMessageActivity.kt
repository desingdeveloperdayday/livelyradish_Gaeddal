package com.hyeyeon2371.gaeddal.mypage.settingmessage.list

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.hyeyeon2371.gaeddal.BR
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.RequestCodeFlag
import com.hyeyeon2371.gaeddal.common.base.BaseActivity
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivitySettingmessageBinding
import com.hyeyeon2371.gaeddal.mypage.settingmessage.write.WriteSettingMessageActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SettingMessageActivity :
    BaseActivity<ActivitySettingmessageBinding, SettingMessageViewModel, SettingMessageActivityNavigator>(),
    SettingMessageActivityNavigator {

    private val vm: SettingMessageViewModel by viewModel { parametersOf(this) }

    override fun getLayout(): Int = R.layout.activity_settingmessage
    override fun injectViewModel(): SettingMessageViewModel = vm

    override fun initView() {
        binding.rvSettingmessageList.adapter = SettingMessageAdapter()
        binding.rvSettingmessageList.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        binding.iclSettingmessageToolbar.title = viewModel.toolbarTitle
        binding.iclSettingmessageToolbar.onClickBack = View.OnClickListener { finishActivity() }
        binding.iclSettingmessageToolbar.onClickAddMessage = View.OnClickListener { redirectWriteMessageActivity() }
        binding.viewModel = viewModel
    }

    override fun finishActivity() {
        finish()
    }

    override fun redirectWriteMessageActivity() {
        startActivityForResult(
            Intent(this, WriteSettingMessageActivity::class.java),
            RequestCodeFlag.WRITE_SETTING_MSG.flag
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                RequestCodeFlag.WRITE_SETTING_MSG.flag -> {
                    // message list refresh
                    data?.getStringExtra("message").let {
                        viewModel.list.get()?.add(it ?: "")
                        viewModel.notifyPropertyChanged(BR.list)
                    }
                }
            }
        }
    }
}

interface SettingMessageActivityNavigator : BaseActivityNavigator {
    fun redirectWriteMessageActivity()
}
