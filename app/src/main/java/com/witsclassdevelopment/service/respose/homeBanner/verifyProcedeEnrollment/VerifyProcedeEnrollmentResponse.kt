package com.witsclassdevelopment.service.respose.homeBanner.verifyProcedeEnrollment

import com.witsclassdevelopment.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VerifyProcedeEnrollmentResponse(
    @SerializedName("data")
    val `data`: Datum,
) : BaseResponse() {
    data class Datum(
        @SerializedName("enrollment_id")
        @Expose
        var enrollmentId: Int? = null
    )
}