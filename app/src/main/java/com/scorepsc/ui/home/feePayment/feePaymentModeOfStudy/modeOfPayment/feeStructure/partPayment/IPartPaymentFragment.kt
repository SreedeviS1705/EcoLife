package com.scorepsc.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.partPayment

import com.scorepsc.service.respose.homeBanner.partPayment.PartPaymentListResponse

interface IPartPaymentFragment {
    fun clickEvent(item: PartPaymentListResponse.Datum)
}