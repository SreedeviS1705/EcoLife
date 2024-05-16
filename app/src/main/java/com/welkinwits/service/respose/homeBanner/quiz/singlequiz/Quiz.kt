package com.welkinwits.service.respose.homeBanner.quiz.singlequiz

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quiz(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("chapter_id")
    @Expose
    var chapterId: String? = null,

    @SerializedName("pass_marks")
    @Expose
    var passMarks: String? = null,

    @SerializedName("max_marks")
    @Expose
    var maxMarks: String? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("added_on")
    @Expose
    var addedOn: String? = null,

    @SerializedName("deleted_on")
    @Expose
    var deletedOn: Any? = null
)