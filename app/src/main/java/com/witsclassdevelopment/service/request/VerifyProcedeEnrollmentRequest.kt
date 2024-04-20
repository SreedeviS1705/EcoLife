package com.witsclassdevelopment.service.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VerifyProcedeEnrollmentRequest(
    @SerializedName("stud_id")
    @Expose
    var studId: String? = null,

    @SerializedName("franchise_id")
    @Expose
    var franchiseId: Int? = null,

    @SerializedName("mode_id")
    @Expose
    var modeId: Int? = null
)