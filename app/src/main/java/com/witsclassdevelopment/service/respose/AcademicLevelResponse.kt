package com.witsclassdevelopment.service.respose


import com.google.gson.annotations.SerializedName

data class AcademicLevelResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("token")
    val token: String
) : BaseResponse() {
    data class Data(
        @SerializedName("ac_id")
        val acId: String,
        @SerializedName("levels")
        val levels: String
    )
}