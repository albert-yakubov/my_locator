package com.stepasha.buildinglocator.retrofit

import android.net.Uri
import com.stepasha.buildinglocator.LoginActivity
import com.stepasha.buildinglocator.LoginActivity.Companion.username
import com.stepasha.buildinglocator.model.*
import com.stepasha.buildinglocator.model.Map
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface LoginServiceSql{

    @POST("map/map")
  //  fun createProperty(@Header("Authorization") authToken: String, @Body newProperty: NewProperty): Call<Void>
    fun createProperty(@Body newProperty: NewProperty) : Call<Void>

    @GET("map/maps")
    //fun getAllProperties(@Header("Authorization") authToken: String): Call<Properties>
    fun getAllMaps() : Call<MutableList<Map>>


    @POST("createnewuser")
    fun createUser(@Body newUser: NewUser): Call<RegisterResponse>
//https://oauth.example.com/token?grant_type=password&username=client_credential&client_secret=CLIENT_SECRET&client_id=CLIENT_ID
//&username=admin1&password=password // @Header ("Authorization") authorization: String, @Header("Content-Type") content_type: String,
    @FormUrlEncoded
    @POST("login?grant_type=password&client_id=lambda-client&client_secret=lambda-secret")
    fun login(@Header ("Authorization") authorization: String, @Header("Content-Type") content_type: String,
              @Field("username") username: String, @Field("password") password: String) : Call<ResponseBody>

    @GET("users/user/name/{userName}")

    fun getUser(@Path("userName")username: String): Call<UserResult>


    companion object {

        const val BASE_URL = "https://ay-mylocator.herokuapp.com/"
        val username = LoginActivity.username
        val u = Uri.parse(username)
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .baseUrl(BASE_URL)
            .build()
    }

}