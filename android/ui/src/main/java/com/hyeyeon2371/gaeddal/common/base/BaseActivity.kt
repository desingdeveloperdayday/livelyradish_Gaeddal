package com.hyeyeon2371.gaeddal.common.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, VM : BaseObservableViewModel, N : BaseActivityNavigator> :
    AppCompatActivity() {
    lateinit var binding: T
    lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = injectViewModel()
        initDataBinding()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayout())
        initView()
        binding.executePendingBindings()
    }

    abstract fun getLayout(): Int
    abstract fun injectViewModel() : VM
    abstract fun initView()

}