package com.scorepsc.ui.home.newsevents

import com.scorepsc.service.respose.NewsResponse

interface INewsListener {
    fun clickEvent(subjectId: NewsResponse.NewsData)
}