package com.welkinwits.service.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ScholarshipUpdateEnrollmentRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("enrollment_id")
    @Expose
    var enrollmentId: String? = null,

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
    var mobile: String? = null
)