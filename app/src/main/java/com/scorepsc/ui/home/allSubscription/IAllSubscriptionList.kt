package com.scorepsc.ui.home.allSubscription

import com.scorepsc.service.respose.homeBanner.subscription.GetAllSubscriptionListResponse

interface IAllSubscriptionList {
    fun clickEvent(item: GetAllSubscriptionListResponse.HomeBannerData?)
}