package com.witsclassdevelopment.ui.home.subject.batch

import com.witsclassdevelopment.service.respose.homeBanner.liveClass.batch.LiveClassBatchResponse

interface IBatchListener {
    fun clickEvent(subjectId: LiveClassBatchResponse.Datum)
}