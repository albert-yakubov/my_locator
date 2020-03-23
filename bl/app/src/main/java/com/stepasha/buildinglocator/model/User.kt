package com.stepasha.buildinglocator.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(

    var id: Int,



    val primaryemail: String? = null,
    var username: String? = null,

    val firstname: String? = null,


    val lastname: String? = null,

    @SerializedName("password")
    val password: String? = null

) : Serializable

data class UserResponse(
    val username: String,
    val id: Int,
    val owner: Boolean,
    val token: String
)
data class Result(val username: String? = null)
data class UserResult(val result: Result)