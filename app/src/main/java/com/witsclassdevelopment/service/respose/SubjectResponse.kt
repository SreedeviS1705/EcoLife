package com.witsclassdevelopment.service.respose


import com.google.gson.annotations.SerializedName

data class SubjectResponse(
    @SerializedName("data")
    val `data`: ArrayList<SubjectData>,
    @SerializedName("academic_level")
    val academicLevel: String,
) : BaseResponse() {
    data class SubjectData(
        @SerializedName("subject_id")
        val subjectId: String,
        @SerializedName("subject_name")
        val subjectName: String,
        @SerializedName("thumbnail")
        val thumbnail: String,
        @SerializedName("chapters_count")
        val chapters_count: String
    )
}