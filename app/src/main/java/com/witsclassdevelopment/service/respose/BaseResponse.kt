package com.witsclassdevelopment.service.respose


import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("message")
    var message: String? = null

    @SerializedName("status")
    var status: Boolean? = null

    @SerializedName("status_code")
    var statusCode: Int? = null
}
