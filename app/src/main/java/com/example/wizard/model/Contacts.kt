package com.example.wizard.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class Contacts : ArrayList<Contacts.Contact>(){
   @Parcelize
    data class Contact(
        @SerializedName("dob")
        val dob: String,
        @SerializedName("email")
        val email: String,
        @SerializedName("firstName")
        val firstName: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("lastName")
        val lastName: String
    ) : Parcelable
}