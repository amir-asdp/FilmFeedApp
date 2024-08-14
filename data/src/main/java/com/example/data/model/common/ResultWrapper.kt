package com.example.data.model.common

sealed class ResultWrapper<out SuccessResultType> {

    data class Success<out SuccessResultType>(val resultValue: SuccessResultType): ResultWrapper<SuccessResultType>()

    data class CancelOrFailure(val exception: Exception): ResultWrapper<Nothing>()

}