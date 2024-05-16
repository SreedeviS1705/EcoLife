package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy

import com.welkinwits.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse

interface IModeOfPayment {
    fun clickEvent(id: FeePaymentModeOfStudyResponse.Datum?)
}