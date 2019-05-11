package com.hyeyeon2371.gaeddal.mypage.changename

import android.app.Activity
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseActivityNavigator
import com.hyeyeon2371.gaeddal.databinding.ActivityChangenameBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChangeNameActivity : AppCompatActivity(), BaseActivityNavigator {
    private lateinit var binding: ActivityChangenameBinding
    private val viewModel: ChangeNameViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_changename)
        binding.iclChangenameToolbar.title = "이름 변경"
        binding.iclChangenameToolbar.onClickBack = View.OnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

        viewModel.name = intent.getStringExtra("name")
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    override fun finishActivity() {
        intent.putExtra("name", viewModel.name)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}