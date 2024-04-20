package com.witsclassdevelopment.ui.home.newsevents

import com.witsclassdevelopment.service.respose.NewsResponse

interface INewsListener {
    fun clickEvent(subjectId: NewsResponse.NewsData)
}