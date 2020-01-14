package com.example.pls

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfo(
    val name: String = "",
    val mobile: String? = ""
)