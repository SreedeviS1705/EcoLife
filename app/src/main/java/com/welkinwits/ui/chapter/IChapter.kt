package com.welkinwits.ui.chapter

import com.welkinwits.service.respose.ChapterResponse

interface IChapter {
    fun clickEvent(it1: ChapterResponse.ChapterData);
}