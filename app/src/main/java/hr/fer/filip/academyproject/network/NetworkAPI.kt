package hr.fer.filip.academyproject.network

import hr.fer.filip.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HEAD
import retrofit2.http.Path

interface NetworkAPI {

    @GET("orgs/{org}/repos?per_page=50")
    suspend fun getRepos(@Path("org") org : String) : List<RepoDetails>

    @GET("repos/{org}/{id}")
    suspend fun getRepoDetails(@Path("org") org : String, @Path("id") id : String) : RepoDetails

    @GET("repos/{org}/{id}/contributors?per_page=50")
    suspend fun getContributors(@Path("org") org : String, @Path("id") id : String) : ArrayList<Contributor>

    @GET("repos/{org}/{id}/pulls?per_page=50")
    suspend fun getPulls(@Path("org") org : String, @Path("id") id : String) : ArrayList<Pull>

    @GET("repos/{org}/{id}/issues?per_page=50")
    suspend fun getIssues(@Path("org") org : String, @Path("id") id : String) : ArrayList<Issue>

    @HEAD("repos/{org}/{id}/contributors")
    suspend fun getContributorsHead(@Path("org") org : String, @Path("id") id : String) : Response<Void>

    @HEAD("repos/{org}/{id}/issues")
    suspend fun getIssuesHead(@Path("org") org : String, @Path("id") id : String) : Response<Void>

    @HEAD("repos/{org}/{id}/pulls")
    suspend fun getPullsHead(@Path("org") org : String, @Path("id") id : String) : Response<Void>
}