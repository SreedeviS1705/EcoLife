package com.witsclassdevelopment.service.request


import com.google.gson.annotations.SerializedName

data class JobDetailsRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("job_id")
    val jobId: Int
)