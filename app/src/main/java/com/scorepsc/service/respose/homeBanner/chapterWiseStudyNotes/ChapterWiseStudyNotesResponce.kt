package com.scorepsc.service.respose.homeBanner.chapterWiseStudyNotes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse


class ChapterWiseStudyNotesResponce(
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

        @SerializedName("notes")
        @Expose
        var notes: String? = null,

        @SerializedName("written_notes")
        @Expose
        var writtenNotes: ArrayList<String>? = null,

        @SerializedName("content")
        @Expose
        var content: ArrayList<Content>? = null,

        @SerializedName("study_docs")
        @Expose
        var studyDocs: ArrayList<String>? = null
    )
}