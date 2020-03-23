package com.stepasha.buildinglocator.model

data class LoginResponse(val error: Boolean, val message: String, val userId: Int, val token: String)

//access_token: "e9163369-2fb7-484b-852e-64ae9c407182"
//token_type: "bearer"
//expires_in: 2760
//scope: "read write trust"
data class ResponseBody(val access_token: String,
                        val token_type: String,
                        val experis_in: Int,
                        val scope: String)