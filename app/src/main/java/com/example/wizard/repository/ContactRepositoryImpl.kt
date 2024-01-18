package com.example.wizard.repository

import android.content.Context
import android.util.Log
import com.example.wizard.model.Contacts
import javax.inject.Inject
import com.example.wizard.utils.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStream

class ContactRepositoryImpl @Inject constructor(
) : ContactRepository {
    override suspend fun getData(context: Context): Result<List<Contacts.Contact>> {
        return try {

            val listContact: MutableList<Contacts.Contact> = mutableListOf()

            val inputStream: InputStream = context.assets.open("data.json")
            val inputString = inputStream.bufferedReader().use{it.readText()}
            val data: List<Contacts.Contact> = Gson().fromJson(
                inputString,
                object : TypeToken<List<Contacts.Contact?>?>() {}.type
            )

            listContact.clear()
            listContact.addAll(data)
            return Result.Success(listContact)
        } catch (e: Exception) {
            Log.e("ApiCalls", "Call error: ${e.localizedMessage}", e.cause)
            Result.Error(Exception(e.cause))
        }
    }
}

