package com.nadafeteiha.marvel.data.network

import com.nadafeteiha.marvel.BuildConfig
import okhttp3.Interceptor
import okhttp3.Interceptor.*
import okhttp3.Request
import okhttp3.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*


class MarvelInterceptor : Interceptor {

    override fun intercept(chain: Chain): Response {
        val timeStamp = System.currentTimeMillis().toString()
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("hash", getHash(timeStamp))
            .addQueryParameter("ts", timeStamp)
            .addQueryParameter("apikey", BuildConfig.PUBLIC_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()

        return chain.proceed(request)
    }


    private fun getHash(timeStamp: String) = calculatedMd5(
        timeStamp +
                BuildConfig.PRIVATE_KEY +
                BuildConfig.PUBLIC_KEY
    )

    private fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    private fun calculatedMd5(text: String): String {
        val messageDigest = getMd5Digest(text)
        val md5 = BigInteger(1, messageDigest).toString(16)
        return "0" * (32 - md5.length) + md5
    }

    private fun getMd5Digest(str: String): ByteArray =
        MessageDigest.getInstance("MD5").digest(str.toByteArray())

    private operator fun String.times(i: Int) = (1..i).fold("") { acc, _ -> acc + this }
}