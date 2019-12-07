package com.omni.roominkotlinfirsttry.domain

data class ViewState(
        var errorMsg: String? = null,
        var loading: Boolean = false,
        var success: Boolean = false
)

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}