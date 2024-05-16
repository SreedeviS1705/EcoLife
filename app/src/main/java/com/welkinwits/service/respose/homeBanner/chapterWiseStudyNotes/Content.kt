package com.welkinwits.service.respose.homeBanner.chapterWiseStudyNotes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("ptype")
    @Expose
    var ptype: String? = null,

    @SerializedName("pdata")
    @Expose
    var pdata: String? = null
)