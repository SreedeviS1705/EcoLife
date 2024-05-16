package com.welkinwits.service.respose


import com.google.gson.annotations.SerializedName

data class ChapterResponse(
    @SerializedName("data")
    val `data`: ArrayList<ChapterData>
) : BaseResponse() {
    data class ChapterData(
        @SerializedName("chapter_id")
        val chapterId: String,
        @SerializedName("chapter_name")
        val chapterName: String,
        @SerializedName("subject_name")
        val subjectName: String
    )
}