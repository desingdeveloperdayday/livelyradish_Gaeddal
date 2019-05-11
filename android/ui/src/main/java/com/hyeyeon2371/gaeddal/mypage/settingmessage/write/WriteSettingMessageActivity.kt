package com.hyeyeon2371.gaeddal.mypage.settingmessage.write

import android.app.Activity
import android.view.View
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseActivity
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivityWritemessageBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WriteSettingMessageActivity :
    BaseActivity<ActivityWritemessageBinding, WriteSettingMessageViewModel, WriteSettingMessageActivityNavigator>(),
    WriteSettingMessageActivityNavigator {

    private val vm: WriteSettingMessageViewModel by viewModel { parametersOf(this) }

    override fun getLayout(): Int = R.layout.activity_writemessage
    override fun injectViewModel(): WriteSettingMessageViewModel = vm

    override fun initView() {
        binding.viewModel = viewModel
        binding.iclWritesettingmessageToolbar.onClickBack = View.OnClickListener { finishActivity() }
        binding.iclWritesettingmessageToolbar.onClickAddMessage = View.OnClickListener { addMessage() }
    }

    override fun finishActivity() {
        finish()
    }

    private fun addMessage() {
        intent.putExtra("message", viewModel.message)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}

interface WriteSettingMessageActivityNavigator : BaseActivityNavigator {

}