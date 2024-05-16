package com.welkinwits.ui.topic

import com.welkinwits.service.respose.TopicResponse

interface ITopic {
    fun clickEvent(item: TopicResponse.TopicData?)
}