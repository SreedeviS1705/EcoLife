package com.scorepsc.service.respose.homeBanner.currentAfairs.byGroup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

class CurrentAfairsByGroupResponse(
    @SerializedName("data")
    val `data`: Data
) : BaseResponse() {
    data class Data(
        @SerializedName("group")
        @Expose
        var group: Group? = null,

        @SerializedName("posts")
        @Expose
        var posts: ArrayList<Post>? = null
    )
}