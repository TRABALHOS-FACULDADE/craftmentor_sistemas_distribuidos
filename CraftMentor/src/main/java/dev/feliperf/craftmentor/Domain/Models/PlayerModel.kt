package dev.feliperf.craftmentor.Domain.Models

import com.google.gson.annotations.SerializedName

data class PlayerModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("points")
    var points: Double,
)