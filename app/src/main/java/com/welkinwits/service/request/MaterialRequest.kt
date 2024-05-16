package com.welkinwits.service.request


import com.google.gson.annotations.SerializedName

data class MaterialRequest(
    @SerializedName("stud_id")
    val studId: String,
    @SerializedName("chapter_id")
    val chapterId: String
)