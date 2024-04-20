package com.witsclassdevelopment.ui.home.allSubscription

import com.witsclassdevelopment.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse

interface IAllSubscriptionList {
    fun clickEvent(item: GetAllSubscriptionListResponse.HomeBannerData?)
}