package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class PartPaymentListRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("package_id")
    val packageId: Int
)