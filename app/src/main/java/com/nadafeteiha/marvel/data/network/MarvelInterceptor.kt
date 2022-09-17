package com.nadafeteiha.marvel.data.network

import com.nadafeteiha.marvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


class MarvelInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
        val url1 = request.url.newBuilder().apply {
            addQueryParameter("hash", "cf44a4da60551e7e6ca10121a0837231")//getHash())
            addQueryParameter("ts", "1663269604186")//getTimestamp())
            addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
        }.build()

        return chain.proceed(request.newBuilder().url(url1).build())
    }

    private fun getTimestamp() = Date().time.toString()

    private fun getHash() = md5Hash(
        getTimestamp() +
                BuildConfig.PRIVATE_KEY +
                BuildConfig.PUBLIC_KEY
    )

    private fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
}