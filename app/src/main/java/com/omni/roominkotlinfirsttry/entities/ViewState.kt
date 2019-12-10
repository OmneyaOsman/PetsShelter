package com.omni.roominkotlinfirsttry.entities

data class ViewState(
        var loading: Boolean = false,
        var success: Boolean = false,
        var errorMsg: String? = null,
        var empty: Boolean? = true
)