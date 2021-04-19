package id.xxx.the.movie.db.data.source.remote

import id.xxx.the.movie.db.data.mapper.toMovieWithRecommendation
import id.xxx.the.movie.db.data.source.remote.network.services.MovieApiService
import id.xxx.the.movie.db.data.source.remote.response.MovieResponse
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.base.IRecommendation
import id.xxx.the.movie.db.data.source.remote.response.model.MovieResult
import javax.inject.Inject

class RemoteMovieDataSource @Inject constructor(
    private val apiService: MovieApiService
) : RemoteBaseDataSource<MovieResult, MovieResponse>() {

    public override suspend fun getDiscover(page: Int) =
        getPage { apiService.getDiscoverMovie(page = page) }

    override suspend fun getRecommendation(id: Int, page: Int) =
        apiService.getRecommendationMovie(id, page)

    override suspend fun getResponse(id: Int) = apiService.getMovie(id)

    override fun <T : MovieResponse> toWithRecommendation(
        dataResponse: T, dataRecommendations: List<PageResponse<MovieResult>>
    ): IRecommendation<MovieResult> = dataResponse.toMovieWithRecommendation(dataRecommendations)
}