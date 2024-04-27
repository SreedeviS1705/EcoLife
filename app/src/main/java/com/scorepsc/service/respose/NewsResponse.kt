package com.scorepsc.service.respose


import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("data")
    val `data`: ArrayList<NewsData>
) : BaseResponse() {
    data class NewsData(
        @SerializedName("id")
        val id: String,
        @SerializedName("heading")
        val heading: String,
        @SerializedName("images")
        val images: String,
        @SerializedName("news")
        val news: String,
        @SerializedName("videos")
        val videos: Any
    )
}