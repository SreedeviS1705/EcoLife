package com.welkinwits.ui.home.newsevents

import com.welkinwits.service.respose.NewsResponse

interface INewsListener {
    fun clickEvent(subjectId: NewsResponse.NewsData)
}