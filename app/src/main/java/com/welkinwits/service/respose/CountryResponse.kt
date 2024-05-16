package com.welkinwits.service.respose


import com.google.gson.annotations.SerializedName

data class CountryResponse(
    @SerializedName("data")
    val `data`: List<Data>
):BaseResponse() {
    data class Data(
        @SerializedName("flag")
        val flag: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("iso")
        val iso: String,
        @SerializedName("iso3")
        val iso3: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("nicename")
        val nicename: String,
        @SerializedName("numcode")
        val numcode: String,
        @SerializedName("phonecode")
        val phonecode: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("min_phone_length")
        val minPhoneLength: String,
        @SerializedName("max_phone_length")
        val maxPhoneLength: String
    )
}