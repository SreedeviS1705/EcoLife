package com.witsclassdevelopment.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.scholarshipRedeem

import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse

data class ScholarshipRedeemResponse(
    @SerializedName("data")
    val `data`: ArrayList<Object>,
) : BaseResponse()