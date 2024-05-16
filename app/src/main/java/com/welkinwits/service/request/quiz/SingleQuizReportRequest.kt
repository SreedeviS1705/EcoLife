package com.welkinwits.service.request.quiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleQuizReportRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("id")
    @Expose
    var id: Int? = null
)