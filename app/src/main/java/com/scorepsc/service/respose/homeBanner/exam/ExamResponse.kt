package com.scorepsc.service.respose.homeBanner.exam

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExamResponse(
    @SerializedName("data")
    val `data`: List<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("exam_id")
        @Expose
        var examId: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("subject_name")
        @Expose
        var subjectName: String? = null,

        @SerializedName("thumbnail")
        @Expose
        var thumbnail: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null,

        @SerializedName("attended")
        @Expose
        var attended: String? = null
    )
}