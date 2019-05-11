package com.hyeyeon2371.gaeddal.mypage.base

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.hyeyeon2371.gaeddal.BR
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.RequestCodeFlag
import com.hyeyeon2371.gaeddal.common.util.SharedPrefersUtil
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.databinding.ActivityMypageBinding
import com.hyeyeon2371.gaeddal.mypage.changename.ChangeNameActivity
import com.hyeyeon2371.gaeddal.mypage.settingmessage.list.SettingMessageActivity
import com.hyeyeon2371.gaeddal.mypage.settingperson.base.SettingPersonActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MypageActivity : AppCompatActivity(), MypageActivityNavigator {
    private val viewModel: MypageViewModel by viewModel { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
    }

    private fun initDataBinding() {
        val binding = DataBindingUtil.setContentView<ActivityMypageBinding>(this, R.layout.activity_mypage)

        SharedPrefersUtil.getValue<User>(SharedPrefersUtil.SESSION_DATA, SharedPrefersUtil.LOGGED_IN_USER).let {
            it?.profileUrl = "https://t1.daumcdn.net/cfile/tistory/997036485C164CB824"
            viewModel.user = it ?: User().apply {
                profileUrl = "https://t1.daumcdn.net/cfile/tistory/997036485C164CB824"
                name = it?.name
            }
        }

        binding.viewModel = viewModel
        binding.executePendingBindings()
    }


    override fun finishActivity() {
        this.finish()
    }

    override fun redirectChangeNameActivity() {
        Intent(this, ChangeNameActivity::class.java).apply {
            putExtra("name", viewModel.user.name)
        }.let {
            startActivityForResult(it, RequestCodeFlag.CHANGE_NAME.flag)
        }
    }

    override fun redirectSettingMessageActivity() {
        startActivity(Intent(this, SettingMessageActivity::class.java))
    }

    override fun redirectSettingPersonActivity() {
        startActivity(Intent(this, SettingPersonActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                RequestCodeFlag.CHANGE_NAME.flag -> {
                    val changedName = data?.getStringExtra("name")

                    SharedPrefersUtil.getValue<User>(
                        SharedPrefersUtil.SESSION_DATA,
                        SharedPrefersUtil.LOGGED_IN_USER
                    ).let { user ->
                        user?.name = changedName

                        SharedPrefersUtil.saveValue(
                            SharedPrefersUtil.SESSION_DATA,
                            SharedPrefersUtil.LOGGED_IN_USER,
                            user ?: User().apply { name = changedName }
                        )
                    }

                    viewModel.user.name = changedName
                    viewModel.notifyPropertyChanged(BR.user)
                }
            }
        }

    }
}