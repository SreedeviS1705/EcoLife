package com.scorepsc.service.respose


import com.google.gson.annotations.SerializedName

data class MaterialResponse(
    @SerializedName("data")
    val `data`: ArrayList<Data>,
) :BaseResponse() {
    data class Data(
        @SerializedName("chapter_name")
        val chapterName: String,
        @SerializedName("path")
        val path: String,
        @SerializedName("pdf_file")
        val pdfFile: String,
        @SerializedName("subject_name")
        val subjectName: String,
        @SerializedName("title")
        val title: String
    )
}