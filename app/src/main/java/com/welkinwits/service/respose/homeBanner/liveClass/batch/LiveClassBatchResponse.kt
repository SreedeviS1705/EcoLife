package com.welkinwits.service.respose.homeBanner.liveClass.batch

import com.welkinwits.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LiveClassBatchResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("batch_id")
        @Expose
        var batchId: String? = null,

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