package com.scorepsc.service.respose.homeBanner.scholarship.scholarshipCreateExam.scholarshipUpdateEnrollment.scholarshipStartExam.submit.scholarshipRedeem

import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

data class ScholarshipRedeemResponse(
    @SerializedName("data")
    val `data`: ArrayList<Object>,
) : BaseResponse()