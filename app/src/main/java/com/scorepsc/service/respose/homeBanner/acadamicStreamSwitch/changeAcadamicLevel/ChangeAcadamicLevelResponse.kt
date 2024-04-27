package com.scorepsc.service.respose.homeBanner.acadamicStreamSwitch.changeAcadamicLevel

import com.google.gson.annotations.SerializedName
import com.scorepsc.service.respose.BaseResponse


class ChangeAcadamicLevelResponse(
    @SerializedName("data")
    val `data`: ArrayList<Any>,
) : BaseResponse() {
}