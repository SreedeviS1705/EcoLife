package com.scorepsc.service.request.scholarshipExamSubmit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScholarshipExamSubmitRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("exam_row_id")
    @Expose
    var examRowId: Int? = null,

    @SerializedName("submit_data")
    @Expose
    var submitData: List<SubmitDatum>? = null
)