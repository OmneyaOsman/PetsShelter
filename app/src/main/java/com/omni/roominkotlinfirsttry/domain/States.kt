package com.omni.roominkotlinfirsttry.domain

data class ViewState(
        val errorMsg: String? = null,
        val loading: Boolean = false,
        val success: Boolean = false
)

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}