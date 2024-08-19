package com.example.filmfeedapp.ui.model

import androidx.annotation.OptIn
import com.example.data.model.common.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

sealed class BaseUiState<out SuccessResultType> {

    data object IsLoading: BaseUiState<Nothing>()
    data class Success<SuccessResultType>(val resultValue: SuccessResultType): BaseUiState<SuccessResultType>()
    data class Error(val errorMessage: String): BaseUiState<Nothing>()


    companion object {

        @kotlin.OptIn(FlowPreview::class)
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
        }.flowOn(Dispatchers.IO)

    }

}