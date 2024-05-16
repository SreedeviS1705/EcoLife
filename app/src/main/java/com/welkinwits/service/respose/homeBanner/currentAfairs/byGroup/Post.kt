package com.welkinwits.service.respose.homeBanner.currentAfairs.byGroup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Post(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,

    @SerializedName("added_on")
    @Expose
    var addedOn: String? = null
)