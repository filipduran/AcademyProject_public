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

        lateinit var service: NetworkAPI


        fun init(context: Context) {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

            val httpCacheDirectory = File(context.cacheDir, "responses")
            val cacheSize = 50 * 1024 * 1024
            val cache = Cache(httpCacheDirectory, cacheSize.toLong())

            //Token no longer valid. Left it just as an example.
            val okHttp = OkHttpClient.Builder()
                .addNetworkInterceptor(httpLoggingInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                    val newRequest = request.newBuilder()
                        .header("Authorization", "token 7cf2340fc808c92fc147aa0112e120ef7f9076c8")
                        .build()
                    return@addInterceptor chain.proceed(newRequest)
                }
                .addNetworkInterceptor { chain ->
                    val response = chain.proceed(chain.request())
                    val maxAge = 14
                    return@addNetworkInterceptor response.newBuilder()
                        .header("Cache-Control", "max-age=" + maxAge)
                        .build()
                }
                .cache(cache)
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