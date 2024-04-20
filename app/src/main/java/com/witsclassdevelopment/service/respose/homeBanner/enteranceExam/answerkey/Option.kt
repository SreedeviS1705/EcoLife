package com.witsclassdevelopment.service.respose.homeBanner.enteranceExam.answerkey

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Option(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("ptype")
    @Expose
    var ptype: String? = null,

    @SerializedName("ptext")
    @Expose
    var ptext: String? = null
)