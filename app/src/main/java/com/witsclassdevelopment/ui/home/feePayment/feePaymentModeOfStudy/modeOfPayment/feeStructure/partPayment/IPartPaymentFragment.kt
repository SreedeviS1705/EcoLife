package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure.partPayment

import com.witsclassdevelopment.service.respose.homeBanner.partPayment.PartPaymentListResponse

interface IPartPaymentFragment {
    fun clickEvent(item: PartPaymentListResponse.Datum)
}