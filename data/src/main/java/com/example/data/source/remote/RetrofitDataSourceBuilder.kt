package com.example.data.source.remote

import com.example.data.constant.Constants.RemoteDataSource.ApiUrl
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDataSourceBuilder {

    private fun getHttpClientBuilder(): OkHttpClient.Builder{
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
    }

    private fun getRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiUrl.API_BASE_URL)
            .client(getHttpClientBuilder().build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
    }

    fun <T> buildDataSource(dataSourceClass: Class<T>): T {
        return getRetrofitBuilder().build().create(dataSourceClass)
    }

}