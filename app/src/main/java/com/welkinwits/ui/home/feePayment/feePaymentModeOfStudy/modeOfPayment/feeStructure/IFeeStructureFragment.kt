package com.welkinwits.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import com.welkinwits.service.respose.homeBanner.feeStructure.FeeStructureResponse

interface IFeeStructureFragment {
    fun clickEvent(item: FeeStructureResponse.Datum)
    fun clickPartPayment(datum: FeeStructureResponse.Datum)
}