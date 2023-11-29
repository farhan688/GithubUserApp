package com.example.githubusersubmission2.data.retrofit

import com.example.githubusersubmission2.data.response.DetailUserResponse
import com.example.githubusersubmission2.data.response.GithubResponse
import com.example.githubusersubmission2.data.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") username: String
    ) : Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<ItemsItem>>

}