package com.scorepsc.ui.home.importantLink

import com.scorepsc.service.respose.homeBanner.importantLink.ImportantLinkResponse

interface IImportantLink {
    fun clickEvent(id: ImportantLinkResponse.Datum)
}