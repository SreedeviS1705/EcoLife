package com.scorepsc.service.respose.homeBanner.studyCenter

import com.scorepsc.service.respose.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StudyCenterResponse(
    @SerializedName("data")
    val `data`: ArrayList<Datum>,
) : BaseResponse() {
    data class Datum(
        @SerializedName("id")
        @Expose
        var id: String? = null,

        @SerializedName("name")
        @Expose
        var name: String? = null,

        @SerializedName("registration")
        @Expose
        var registration: String? = null,

        @SerializedName("authorised_persons")
        @Expose
        var authorisedPersons: String? = null,

        @SerializedName("documents")
        @Expose
        var documents: String? = null,

        @SerializedName("state")
        @Expose
        var state: String? = null,

        @SerializedName("district")
        @Expose
        var district: String? = null,

        @SerializedName("address")
        @Expose
        var address: String? = null,

        @SerializedName("mobile")
        @Expose
        var mobile: String? = null,

        @SerializedName("email")
        @Expose
        var email: String? = null
    )
}