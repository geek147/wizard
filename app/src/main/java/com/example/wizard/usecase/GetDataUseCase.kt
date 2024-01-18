package com.example.wizard.usecase

import android.content.Context
import com.example.wizard.model.Contacts
import com.example.wizard.repository.ContactRepository
import javax.inject.Inject
import com.example.wizard.utils.Result

class GetDataUseCase @Inject constructor(
    private val repository: ContactRepository
) : BaseCaseWrapper<List<Contacts.Contact>, GetDataUseCase.Params>() {

    override suspend fun build(params: Params?): Result<List<Contacts.Contact>> {
        if (params == null) throw IllegalArgumentException("Params should not be null")
        return repository.getData(params.context)
    }

    class Params(val context: Context)

}