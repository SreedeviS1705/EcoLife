package com.welkinwits.service.respose.homeBanner.acadamicStreamSwitch.changeAcadamicLevel

import com.google.gson.annotations.SerializedName
import com.welkinwits.service.respose.BaseResponse


class ChangeAcadamicLevelResponse(
    @SerializedName("data")
    val `data`: ArrayList<Any>,
) : BaseResponse() {
}