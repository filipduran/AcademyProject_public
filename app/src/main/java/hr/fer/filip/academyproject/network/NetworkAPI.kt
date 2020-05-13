package hr.fer.filip.academyproject.network

import hr.fer.filip.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Path

interface NetworkAPI {

    @GET("orgs/{org}/repos?per_page=50")
    suspend fun getRepos(@Path("org") org : String) : List<Repository>

    @GET("repos/square/{id}")
    suspend fun getRepoDetails(@Path("id") id : String) : RepoDetails

    @GET("repos/square/{id}/contributors?per_page=50")
    suspend fun getContributors(@Path("id") id : String) : ArrayList<Contributor>

    @HEAD("repos/square/{id}/issues")
    suspend fun getContributorsHead(@Path("id") id : String) : Response<Void>

    @GET("repos/square/{id}/pulls?per_page=50")
    suspend fun getPulls(@Path("id") id : String) : ArrayList<Pull>

    @GET("repos/square/{id}/issues?per_page=50")
    suspend fun getIssues(@Path("id") id : String) : ArrayList<Issue>
}