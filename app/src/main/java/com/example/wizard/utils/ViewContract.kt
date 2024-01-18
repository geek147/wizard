package com.example.wizard.utils

import android.content.Context
import com.example.wizard.model.Contacts


sealed class Intent {
    data class GetData(val context: Context): Intent()
}

data class State(
    val showLoading: Boolean = false,
    val listData: List<Contacts.Contact> = listOf(),
    val viewState: ViewState = ViewState.Idle
)

sealed class ViewState {
    object Idle : ViewState()
    object SuccessGetData: ViewState()
    object EmptyGetData : ViewState()
    object Error: ViewState()
}
