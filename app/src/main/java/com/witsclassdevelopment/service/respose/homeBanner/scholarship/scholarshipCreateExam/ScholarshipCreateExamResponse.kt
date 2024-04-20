package com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class ScholarshipCreateExamResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("stud_id")
        @Expose
        var studId: String? = null,

        @SerializedName("exam_id")
        @Expose
        var examId: String? = null,

        @SerializedName("class")
        @Expose
        var class_: String? = null,

        @SerializedName("school")
        @Expose
        var school: String? = null,

        @SerializedName("place")
        @Expose
        var place: String? = null,

        @SerializedName("mobile")
        @Expose
        var mobile: String? = null,

        @SerializedName("scholarship_status")
        @Expose
        var scholarshipStatus: String? = null,

        @SerializedName("redeem_status")
        @Expose
        var redeemStatus: String? = null,

        @SerializedName("added_on")
        @Expose
        var addedOn: String? = null,

        @SerializedName("deleted_on")
        @Expose
        var deletedOn: Any? = null
    )
}