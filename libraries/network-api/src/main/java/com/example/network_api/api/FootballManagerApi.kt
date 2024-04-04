package com.example.network_api.api

import com.example.network_api.entity.Join
import com.example.network_api.entity.Login
import com.example.network_api.response.JoinResponse
import com.example.network_api.response.LoginResponse
import com.example.network_api.response.ModifyUserInfoResponse
import com.example.network_api.response.SignOutResponse
import com.example.network_api.response.UserInfoResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface FootballManagerApi {
    @POST("/api/user-service/login")
    suspend fun login(
        @Body loginReq: Login
    ): Response<LoginResponse>

    @POST("/api/user-service/register")
    suspend fun Join(
        @Body JoinReq:Join
    ):Response<JoinResponse>


    @GET("/api/user-service/users")
    suspend fun getUserInfo(): Response<UserInfoResponse>

    @Multipart
    @PUT("/api/user-service/users")
    suspend fun modifyUserInfo(
        @Part("name") name: RequestBody,
        @Part("age") age: RequestBody,
        @Part("height") height: RequestBody,
        @Part("sex") sex: RequestBody,
        @Part("position") position: RequestBody,
        @Part("foot") foot: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<ModifyUserInfoResponse>

    @DELETE("/api/user-service/users")
    suspend fun signOut(): Response<SignOutResponse>
}