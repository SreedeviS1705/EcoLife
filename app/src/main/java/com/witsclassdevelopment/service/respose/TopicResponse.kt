package com.witsclassdevelopment.service.respose


import com.google.gson.annotations.SerializedName

data class TopicResponse(
    @SerializedName("data")
    val `data`: ArrayList<TopicData>,
) : BaseResponse() {
    data class TopicData(
        @SerializedName("chapter_name")
        val chapterName: String,
        @SerializedName("subject_name")
        val subjectName: String,
        @SerializedName("topic_id")
        val topicId: String,
        @SerializedName("topic_name")
        val topicName: String,
        @SerializedName("video_link")
        val videoLink: String,
        @SerializedName("thumbnail")
        val thumbnail: String
    )
}