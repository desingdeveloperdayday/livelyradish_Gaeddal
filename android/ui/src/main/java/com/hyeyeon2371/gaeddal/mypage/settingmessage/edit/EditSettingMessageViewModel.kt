package com.hyeyeon2371.gaeddal.mypage.settingmessage.edit

import com.hyeyeon2371.gaeddal.common.base.BaseObservableViewModel

class EditSettingMessageViewModel(private val navigator: EditSettingMessageActivityNavigator) :
    BaseObservableViewModel() {
    var message = ""
    var editedMessage = ""

    fun onClickBack() {
        navigator.finishActivity()
    }

    fun onClickDeleteMessage() {
        navigator.deleteMessage()
    }

    fun onClickEditMessage() {
        if(message == editedMessage){
            navigator.showMessage("변경된 내용이 없습니다.")
        }else {
            navigator.editMessage()
        }
    }
}