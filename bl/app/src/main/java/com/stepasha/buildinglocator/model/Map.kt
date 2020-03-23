package com.stepasha.buildinglocator.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Map(
    var id: Int,

    val posteddate: String? = null,

    val imageurl: String? = null,

    val address: String? = null,

    val description: String? = null,

    val additionalInfo: String? = null
) : Serializable

data class NewProperty (
    var imageurl: String,
    var address: String,
    var description: String,
    var additionalInfo: String

): Serializable
data class Maps(val maps: MutableList<Map>)

