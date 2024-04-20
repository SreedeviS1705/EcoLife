package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy

import com.witsclassdevelopment.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse

interface IModeOfPayment {
    fun clickEvent(id: FeePaymentModeOfStudyResponse.Datum?)
}