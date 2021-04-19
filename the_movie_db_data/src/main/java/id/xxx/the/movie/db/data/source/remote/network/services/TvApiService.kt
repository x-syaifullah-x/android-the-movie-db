package id.xxx.the.movie.db.data.source.remote.network.services

import id.xxx.the.movie.db.data.BuildConfig
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.TvResponse
import id.xxx.the.movie.db.data.source.remote.response.model.TvResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApiService {
    @GET("3/tv/{id}?api_key=${BuildConfig.API_KEY}")
    suspend fun getTv(
        @Path("id") id: Int,
        @Query("language") language: String = "en-US",
    ): TvResponse?

    @GET("3/tv/{id}/recommendations?api_key=${BuildConfig.API_KEY}")
    suspend fun getRecommendationTv(
        @Path("id") id: Int,
        @Query("page") page: Int,
        @Query("language") language: String = "en-US",
    ): PageResponse<TvResult>

    @GET("3/discover/tv?api_key=${BuildConfig.API_KEY}")
    suspend fun getDiscoverTv(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): PageResponse<TvResult>
}