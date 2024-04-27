package com.scorepsc.ui.home.subject.batch

import com.scorepsc.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse

interface IBatchListener {
    fun clickEvent(subjectId: LiveClassBatchResponse.Datum)
}