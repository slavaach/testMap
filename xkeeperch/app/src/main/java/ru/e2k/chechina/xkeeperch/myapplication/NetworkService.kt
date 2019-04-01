package ru.e2k.chechina.xkeeperch.myapplication


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {
    private val mRetrofit: Retrofit

    private var mInstance: NetworkService? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com"


    val jsonApi: JSONPlaceHolderApi
        get() = mRetrofit.create(JSONPlaceHolderApi::class.java)

    init {
        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    companion object {
        val instance = NetworkService()
    }

}
