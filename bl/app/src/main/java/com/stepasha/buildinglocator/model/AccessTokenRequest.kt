package com.stepasha.buildinglocator.model

import java.io.Serializable


class AccessTokenRequest (
    private var username: String,
    private var password: String,
    private var client_id: String,
    private var client_secret: String,
    private var grant_type: String

):Serializable