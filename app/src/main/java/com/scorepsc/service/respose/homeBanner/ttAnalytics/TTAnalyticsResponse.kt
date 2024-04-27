package com.scorepsc.service.respose.homeBanner.ttAnalytics

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse

class TTAnalyticsResponse(
    @SerializedName("data")
    val `data`: HomeBannerData
) : BaseResponse() {
    data class HomeBannerData(
        @SerializedName("video_classes_watched")
        @Expose
        var videoClassesWatched: Int? = null,

        @SerializedName("study_materials_watched")
        @Expose
        var studyMaterialsWatched: Int? = null,

        @SerializedName("quiz_attended_pass")
        @Expose
        var quizAttendedPass: Int? = null,

        @SerializedName("quiz_attended_failed")
        @Expose
        var quizAttendedFailed: Int? = null,

        @SerializedName("entrance_attented_pass")
        @Expose
        var entranceAttentedPass: Int? = null,

        @SerializedName("entrance_attented_failed")
        @Expose
        var entranceAttentedFailed: Int? = null,

        @SerializedName("general_exam_pass")
        @Expose
        var generalExamPass: Int? = null,

        @SerializedName("general_exam_failed")
        @Expose
        var generalExamFailed: Int? = null
    )
}