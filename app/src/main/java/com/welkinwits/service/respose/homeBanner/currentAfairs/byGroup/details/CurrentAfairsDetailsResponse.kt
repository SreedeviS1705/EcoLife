package com.welkinwits.service.respose.homeBanner.currentAfairs.byGroup.details

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse

class CurrentAfairsDetailsResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
        @SerializedName("title")
        @Expose
        var title: String? = null,

        @SerializedName("image")
        @Expose
        var image: String? = null,

        @SerializedName("description")
        @Expose
        var description: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null
    )
}