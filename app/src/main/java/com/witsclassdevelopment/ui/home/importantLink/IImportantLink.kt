package com.witsclassdevelopment.ui.home.importantLink

import com.witsclassdevelopment.service.respose.homeBanner.importantLink.ImportantLinkResponse

interface IImportantLink {
    fun clickEvent(id: ImportantLinkResponse.Datum)
}