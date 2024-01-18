package com.example.wizard.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.wizard.dispatchers.CoroutineDispatchers
import com.example.wizard.ui.base.BaseViewModel
import com.example.wizard.usecase.GetDataUseCase
import com.example.wizard.utils.Intent
import com.example.wizard.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.example.wizard.utils.Result
import com.example.wizard.utils.ViewState

@HiltViewModel
class SharedViewModel @Inject constructor (
    private val getDataUseCase: GetDataUseCase,
    private val ioDispatchers: CoroutineDispatchers
)  : BaseViewModel<Intent, State>(State()){
    override fun onIntentReceived(intent: Intent) {
        when (intent) {
           is Intent.GetData-> getData(intent.context)
        }
    }

    private fun getData(context: Context) {
        setState {
            copy(
                showLoading = true,
            )
        }

        val param = GetDataUseCase.Params(context)

        viewModelScope.launch {
            when (
                val result = withContext(ioDispatchers.io) {
                    getDataUseCase(param)
                }
            ) {
                is Result.Success -> {
                    if (result.data.isEmpty()) {
                        setState {
                            copy(
                                listData = emptyList(),
                                showLoading = false,
                                viewState = ViewState.EmptyGetData
                            )
                        }
                    } else {
                        setState {
                            copy(
                                listData = result.data,
                                showLoading = false,
                                viewState = ViewState.SuccessGetData
                            )
                        }
                    }
                }
                is Result.Error -> {
                    setState {
                        copy(
                            listData = emptyList(),
                            showLoading = false,
                            viewState = ViewState.Error
                        )
                    }
                }

                else -> {}
            }
        }
    }
}