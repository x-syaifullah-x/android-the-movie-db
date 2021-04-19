package id.xxx.the.movie.db.data.source.remote

import id.xxx.the.movie.db.data.mapper.toTvWithTvRecommendation
import id.xxx.the.movie.db.data.source.remote.network.services.TvApiService
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.TvResponse
import id.xxx.the.movie.db.data.source.remote.response.base.IRecommendation
import id.xxx.the.movie.db.data.source.remote.response.model.TvResult
import javax.inject.Inject

class RemoteTvDataSource @Inject constructor(
    private val apiService: TvApiService
) : RemoteBaseDataSource<TvResult, TvResponse>() {

    public override suspend fun getDiscover(page: Int) =
        getPage { apiService.getDiscoverTv(page = page) }

    override suspend fun getRecommendation(id: Int, page: Int) =
        apiService.getRecommendationTv(id, page)

    override suspend fun getResponse(id: Int) = apiService.getTv(id)

    override fun <T : TvResponse> toWithRecommendation(
        dataResponse: T, dataRecommendations: List<PageResponse<TvResult>>
    ): IRecommendation<TvResult> = dataResponse.toTvWithTvRecommendation(dataRecommendations)
}