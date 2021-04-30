package com.mysoft.oceanizeandroidtest.data.model

import com.google.gson.annotations.SerializedName

data class CommandInfo(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("host") val host: String,
    @SerializedName("port") val port: Int,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("command") val command: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("status") val status: Int
)