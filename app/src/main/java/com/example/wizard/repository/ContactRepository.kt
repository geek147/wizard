package com.example.wizard.repository

import android.content.Context
import com.example.wizard.model.Contacts
import com.example.wizard.utils.Result

interface ContactRepository {

    suspend fun getData(context: Context): Result<List<Contacts.Contact>>

}
