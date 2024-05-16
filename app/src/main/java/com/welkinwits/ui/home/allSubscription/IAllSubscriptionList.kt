package com.welkinwits.ui.home.allSubscription

import com.welkinwits.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse

interface IAllSubscriptionList {
    fun clickEvent(item: GetAllSubscriptionListResponse.HomeBannerData?)
}