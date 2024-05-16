package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.partPayment

import com.welkinwits.service.respose.homeBanner.partPayment.PartPaymentListResponse

interface IPartPaymentFragment {
    fun clickEvent(item: PartPaymentListResponse.Datum)
}