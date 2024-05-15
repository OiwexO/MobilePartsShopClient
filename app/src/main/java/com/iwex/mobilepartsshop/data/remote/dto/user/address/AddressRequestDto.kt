package com.iwex.mobilepartsshop.data.remote.dto.user.address

import com.google.gson.annotations.SerializedName

data class AddressRequestDto(
    @SerializedName("postalCode") val postalCode: Int,
    @SerializedName("country") val country: String,
    @SerializedName("state") val state: String,
    @SerializedName("city") val city: String,
    @SerializedName("street") val street: String,
    @SerializedName("buildingNumber") val buildingNumber: String
)
