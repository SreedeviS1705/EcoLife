package com.witsclassdevelopment.service.respose.homeBanner.acadamicStreamSwitch.changeAcadamicLevel

import com.google.gson.annotations.SerializedName
import com.witsclassdevelopment.service.respose.BaseResponse


class ChangeAcadamicLevelResponse(
    @SerializedName("data")
    val `data`: ArrayList<Any>,
) : BaseResponse() {
}