package com.welkinwits.ui.home.subject.batch

import com.welkinwits.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse

interface IBatchListener {
    fun clickEvent(subjectId: LiveClassBatchResponse.Datum)
}