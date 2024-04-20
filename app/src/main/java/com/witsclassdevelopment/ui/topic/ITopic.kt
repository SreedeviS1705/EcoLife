package com.witsclassdevelopment.ui.topic

import com.witsclassdevelopment.service.respose.TopicResponse

interface ITopic {
    fun clickEvent(item: TopicResponse.TopicData?)
}