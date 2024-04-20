package com.witsclassdevelopment.service.respose.homeBanner.currentAfairs

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

class CurrentAfairsResponse(
    @SerializedName("data")
    val `data`: ArrayList<Data>
) : BaseResponse() {
    data class Data(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null
    )
}