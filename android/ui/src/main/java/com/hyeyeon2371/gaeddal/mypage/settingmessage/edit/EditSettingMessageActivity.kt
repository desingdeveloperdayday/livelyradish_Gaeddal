package com.hyeyeon2371.gaeddal.mypage.settingmessage.edit

import android.app.Activity
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.ResultCodeFlag
import com.hyeyeon2371.gaeddal.common.base.BaseActivity
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivityEditmessageBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditSettingMessageActivity :
    BaseActivity<ActivityEditmessageBinding, EditSettingMessageViewModel, BaseActivityNavigator>(),
    EditSettingMessageActivityNavigator {

    private val vm: EditSettingMessageViewModel by viewModel { parametersOf(this) }
    override fun getLayout(): Int = R.layout.activity_editmessage
    override fun injectViewModel(): EditSettingMessageViewModel = vm

    override fun initView() {
        viewModel.message = intent.getStringExtra("message")
        viewModel.editedMessage = viewModel.message
        binding.iclEditsettingmessageToolbar.viewModel = viewModel
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.etEditsettingmessage.setSelection(binding.etEditsettingmessage.length())
        binding.etEditsettingmessage.requestFocus()
    }

    override fun finishActivity() {
        finish()
    }

    override fun deleteMessage() {
        intent.putExtra("resultFlag", ResultCodeFlag.DELETE_SETTING_MSG.flag)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    override fun editMessage() {
        intent.putExtra("resultFlag", ResultCodeFlag.EDIT_SETTING_MSG.flag)
        intent.putExtra("message", viewModel.editedMessage)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}

interface EditSettingMessageActivityNavigator : BaseActivityNavigator {
    fun deleteMessage()
    fun editMessage()
}