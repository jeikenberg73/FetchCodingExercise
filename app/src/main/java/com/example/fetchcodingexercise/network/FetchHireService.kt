package com.example.fetchcodingexercise.network

import com.example.fetchcodingexercise.model.Hire
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * This is the base URL of the site to retrieve the hire list.
 */
private const val URL =  "https://fetch-hiring.s3.amazonaws.com/"

/**
 * This variable is used to build an instance that will be used to direct retrofit to the location
 *  to get the hire list and labels it as a JSON file.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(URL)
    .build()

/**
 * This interface is used to take the JSON file and put the objects in it into a list of Hire object.
 */
interface HireService {
    @GET("hiring.json")
    suspend fun getHireList(): List<Hire>
}

/**
 * This object is used to create an instance of HireService and using the retrofit instance, create
 * a list of Hire objects out of the JSON file.
 */
object FetchHireApi {
    val service : HireService by lazy {
        retrofit.create(HireService::class.java)
    }
}