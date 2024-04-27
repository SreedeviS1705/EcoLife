package com.scorepsc.ui.chapter

import com.scorepsc.service.respose.ChapterResponse

interface IChapter {
    fun clickEvent(it1: ChapterResponse.ChapterData);
}