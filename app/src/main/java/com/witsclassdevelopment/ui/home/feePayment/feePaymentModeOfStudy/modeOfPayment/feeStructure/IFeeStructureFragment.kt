package com.witsclassdevelopment.ui.home.feePayment.feePaymentModeOfStudy.modeOfPayment.feeStructure

import com.witsclassdevelopment.service.respose.homeBanner.feeStructure.FeeStructureResponse

interface IFeeStructureFragment {
    fun clickEvent(item: FeeStructureResponse.Datum)
    fun clickPartPayment(datum: FeeStructureResponse.Datum)
}