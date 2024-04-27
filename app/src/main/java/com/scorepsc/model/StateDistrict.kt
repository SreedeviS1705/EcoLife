package com.scorepsc.model


import com.google.gson.annotations.SerializedName

data class StateDistrict(
    @SerializedName("states")
    val states: List<State>
) {
    data class State(
        @SerializedName("districts")
        val districts: List<String>,
        @SerializedName("state")
        val state: String
    )
}