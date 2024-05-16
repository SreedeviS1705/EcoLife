package com.welkinwits.ui.home.feePayment.partPayment

import com.welkinwits.service.respose.homeBanner.myPartPayment.MySubscriptionPackageListResponse

interface IMySubscriptionPackageListing {
    fun itemClickEvent(get: MySubscriptionPackageListResponse.Datum?)
}