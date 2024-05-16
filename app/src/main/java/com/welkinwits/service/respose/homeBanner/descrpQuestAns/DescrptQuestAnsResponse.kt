package com.welkinwits.service.respose.homeBanner.descrpQuestAns

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse
import com.welkinwits.service.respose.homeBanner.chapterWiseStudyNotes.Content

class DescrptQuestAnsResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("chapter_id")
        @Expose
        var chapterId: String? = null,

        @SerializedName("question")
        @Expose
        var question: String? = null,

        @SerializedName("answer")
        @Expose
        var answer: String? = null,

        @SerializedName("info")
        @Expose
        var info: ArrayList<String>? = null,

        @SerializedName("content")
        @Expose
        var content: ArrayList<Content>? = null,

        @SerializedName("docs")
        @Expose
        var docs: ArrayList<String>? = null
    )
}