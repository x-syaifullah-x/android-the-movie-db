package id.xxx.the.movie.db.data.repository

import androidx.paging.*
import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import id.xxx.base.domain.model.ApiResponse
import id.xxx.the.movie.db.data.mapper.*
import id.xxx.the.movie.db.data.source.local.TvDataSource
import id.xxx.the.movie.db.data.source.mediator.DiscoverTvRemoteMediator
import id.xxx.the.movie.db.data.source.remote.RemoteTvDataSource
import id.xxx.the.movie.db.data.source.remote.response.model.TvWithTvRecommendation
import id.xxx.the.movie.db.domain.model.ItemModel
import id.xxx.the.movie.db.domain.repository.TvRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class TvRepositoryImpl @Inject constructor(
    private val remoteTv: RemoteTvDataSource,
    private val tv: TvDataSource,
    private val discoverTvRemoteMediator: DiscoverTvRemoteMediator
) : TvRepository {

    override fun getDiscoverTv(): Flow<PagingData<ItemModel>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        remoteMediator = discoverTvRemoteMediator,
        pagingSourceFactory = { tv.getTvResult() }
    ).flow.map { it.map { data -> data.toItemModel(data.name) } }

    override fun getTv(id: Int) = NetworkBoundResourceImpl(
        blockRequest = { remoteTv.getResponseWithRecommendation<TvWithTvRecommendation>(id) },
        blockResult = { tv.getTv(id).map { it.toDetailTvModel() } },
        blockOnRequest = { apiResponse, b ->
            if (apiResponse is ApiResponse.Success) {
                val it = apiResponse.data
                val tvEntity = it.toTvEntity()
                val genres = it.genres.map { data -> data.toTvGenreEntity() }
                val recommendations = it.recommendations.map { data -> data.toPageEmbedded() }
                tv.insert(tvEntity, genres, recommendations)
            }
        }
    ).asFlow()
}