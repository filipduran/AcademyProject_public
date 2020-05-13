package hr.fer.filip.academyproject.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Network {

    companion object {

        lateinit var service : NetworkAPI

        fun init(context: Context) {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

            val httpCacheDirectory = File(context.cacheDir,"responses")
            val cacheSize = 50 * 1024 * 1024
            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            val okHttp = OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
//                .cache(cache)
                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttp)
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            service = retrofit.create(NetworkAPI::class.java)

        }
    }

}