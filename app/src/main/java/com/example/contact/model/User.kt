package com.example.contact.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val photo: String
) : Parcelable