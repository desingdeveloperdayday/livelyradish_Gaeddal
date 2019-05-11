package com.hyeyeon2371.gaeddal.mypage.settingperson.list

import android.view.View
import com.hyeyeon2371.gaeddal.R
import com.hyeyeon2371.gaeddal.common.base.BaseRecyclerViewAdapter
import com.hyeyeon2371.gaeddal.common.base.BaseViewHolder
import com.hyeyeon2371.gaeddal.data.entity.User
import com.hyeyeon2371.gaeddal.databinding.ItemSettingpersonlistBinding

class SettingPersonListAdapter : BaseRecyclerViewAdapter<ItemSettingpersonlistBinding, User>() {
    lateinit var viewModel: SettingPersonListViewModel
    override fun getLayout(): Int = R.layout.item_settingpersonlist

    override fun viewHolder(layout: Int, view: View): BaseViewHolder = Holder(binding)

    inner class Holder(binding: ItemSettingpersonlistBinding) : BaseViewHolder(binding.root) {
        override fun bind(item: Any) {
            binding.llSettingpersonlistContainer.setOnLongClickListener {
                viewModel.selectedItemPos = adapterPosition
                viewModel.setItemSelected()
                false
            }
            binding.user = item as User
            binding.executePendingBindings()
        }
    }
}