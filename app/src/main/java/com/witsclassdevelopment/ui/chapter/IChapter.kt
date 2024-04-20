package com.witsclassdevelopment.ui.chapter

import com.witsclassdevelopment.service.respose.ChapterResponse

interface IChapter {
    fun clickEvent(it1: ChapterResponse.ChapterData);
}