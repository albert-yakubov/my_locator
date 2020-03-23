package com.stepasha.buildinglocator.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserLogin (
    val username: String,
    val password: String
) : Serializable

data class RequestBody(
    val grant_type: String,
    //username: "test1"
    //password: "test1"
    //scope: ""

    val username: String,
    val password: String,
    val scope: String = ""):Serializable


//trying to request a token when logging in
data class Token(val token_name: String,
                    val scope: String,
                 val token_type: String,
                 var expires_in: Long,
                 val access_token: String) : Serializable

data class WeirdRequest(
    //login?grant_type=password&client_id=lambda-client&client-secret=lambda-secret&username=&password=
        val grant_type: String = "password",
        val client_id: String = "lambda-client",
        val client_secret: String = "lambda-secret",
        val username: String = "username",
        val password: String = "password"

): Serializable