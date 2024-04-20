package com.witsclassdevelopment.service.respose.homeBanner.liveClass.liveclasses

import com.witsclassdevelopment.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LiveClassesResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("class_id")
        @Expose
        var classId: String? = null,

        @SerializedName("class_name")
        @Expose
        var className: String? = null,

        @SerializedName("online_url")
        @Expose
        var onlineUrl: String? = null,

        @SerializedName("batch_name")
        @Expose
        var batchName: String? = null,

        @SerializedName("subject_name")
        @Expose
        var subjectName: String? = null,

        @SerializedName("tutor_name")
        @Expose
        var tutorName: String? = null
    )
}