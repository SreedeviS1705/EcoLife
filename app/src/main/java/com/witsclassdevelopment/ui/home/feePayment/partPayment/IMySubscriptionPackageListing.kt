package com.witsclassdevelopment.ui.home.feePayment.partPayment

import com.witsclassdevelopment.service.respose.homeBanner.myPartPayment.MySubscriptionPackageListResponse

interface IMySubscriptionPackageListing {
    fun itemClickEvent(get: MySubscriptionPackageListResponse.Datum?)
}