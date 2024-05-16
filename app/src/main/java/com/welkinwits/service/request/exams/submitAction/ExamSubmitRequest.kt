package com.welkinwits.service.request.exams.submitAction

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExamSubmitRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("record_id")
    @Expose
    var recordId: Int? = null,

    @SerializedName("submit_data")
    @Expose
    var submitData: List<SubmitDatum>? = null
)