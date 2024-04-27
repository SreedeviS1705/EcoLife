package com.scorepsc.ui.home.feePayment.feePaymentModeOfStudy

import com.scorepsc.service.respose.homeBanner.feePayment.FeePaymentModeOfStudyResponse

interface IModeOfPayment {
    fun clickEvent(id: FeePaymentModeOfStudyResponse.Datum?)
}