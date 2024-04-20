package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.answerkey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Explanation(
    @SerializedName("ptype")
    @Expose
    var ptype: String? = null,

    @SerializedName("pdata")
    @Expose
    var pdata: String? = null
)