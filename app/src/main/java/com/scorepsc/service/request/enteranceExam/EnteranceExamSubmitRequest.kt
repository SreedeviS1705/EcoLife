package com.scorepsc.service.request.enteranceExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EnteranceExamSubmitRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("enrollment_id")
    @Expose
    var examRowId: Int? = null,

    @SerializedName("submit_data")
    @Expose
    var submitData: List<EnteranceSubmitDatum>? = null
)