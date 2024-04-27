package com.scorepsc.ui.topic

import com.scorepsc.service.respose.TopicResponse

interface ITopic {
    fun clickEvent(item: TopicResponse.TopicData?)
}