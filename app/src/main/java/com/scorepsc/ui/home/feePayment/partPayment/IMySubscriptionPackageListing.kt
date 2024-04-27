package com.scorepsc.ui.home.feePayment.partPayment

import com.scorepsc.service.respose.homeBanner.myPartPayment.MySubscriptionPackageListResponse

interface IMySubscriptionPackageListing {
    fun itemClickEvent(get: MySubscriptionPackageListResponse.Datum?)
}