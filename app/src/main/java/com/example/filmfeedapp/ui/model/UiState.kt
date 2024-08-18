package com.example.filmfeedapp.ui.model

import com.example.data.model.common.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class UiState<out SuccessResultType> {

    data object Empty: UiState<Nothing>()
    data object IsLoading: UiState<Nothing>()
    data class Success<SuccessResultType>(val result: SuccessResultType): UiState<SuccessResultType>()
    data class Error(val message: String): UiState<Nothing>()


    companion object {

        inline fun <T> safeCall(
            crossinline block: () -> Flow<ResultWrapper<T>>
        ) = block().map { result ->
            when(result){
                is ResultWrapper.Success -> Success(result.resultValue)
                is ResultWrapper.CancelOrFailure -> Error(result.exception.toString())
            }
        }.onStart {
            emit(IsLoading)
        }.catch {
            emit(Error(it.toString()))
        }

    }

}