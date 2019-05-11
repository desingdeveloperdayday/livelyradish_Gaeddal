package com.hyeyeon2371.gaeddal.mypage.settingmessage.list

import android.view.View
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseRecyclerViewAdapter
import com.hyeyeon2371.gaeddal.common.base.BaseViewHolder
import com.hyeyeon2371.gaeddal.databinding.ItemSettingmessageBinding

class SettingMessageAdapter : BaseRecyclerViewAdapter<ItemSettingmessageBinding, String>() {
    lateinit var viewModel : SettingMessageViewModel

    override fun getLayout(): Int {
        return R.layout.item_settingmessage
    }

    override fun viewHolder(layout: Int, view: View): BaseViewHolder {
        return Holder(binding)
    }

    inner class Holder(binding: ItemSettingmessageBinding) : BaseViewHolder(binding.root) {
        override fun bind(item: Any) {
            binding.message = item as String
            binding.clSettingmessageContainer.setOnClickListener { viewModel.onClickMessage(adapterPosition) }
            binding.executePendingBindings()
        }
    }
}