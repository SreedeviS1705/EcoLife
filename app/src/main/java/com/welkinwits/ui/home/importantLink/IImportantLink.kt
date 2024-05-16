package com.welkinwits.ui.home.importantLink

import com.welkinwits.service.respose.homeBanner.importantLink.ImportantLinkResponse

interface IImportantLink {
    fun clickEvent(id: ImportantLinkResponse.Datum)
}