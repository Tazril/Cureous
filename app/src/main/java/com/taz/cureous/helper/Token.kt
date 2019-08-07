package com.taz.cureous.helper

import com.google.gson.annotations.SerializedName


data class Token(
    @SerializedName("Token") var token: String,
    @SerializedName("ValidThrough") var port: Int
)