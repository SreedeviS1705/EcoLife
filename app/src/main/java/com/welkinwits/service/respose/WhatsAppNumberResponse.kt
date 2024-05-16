package com.welkinwits.service.respose


import com.google.gson.annotations.SerializedName

data class WhatsAppNumberResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
        @SerializedName("whatsapp")
        val whatsapp: String?
    )
}