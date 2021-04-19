package id.xxx.the.movie.db.data.source.mediator

import androidx.paging.ExperimentalPagingApi
import id.xxx.the.movie.db.data.source.local.DiscoverTvDataSource
import id.xxx.the.movie.db.data.source.local.entity.discover.tv.DiscoverTvResultEntity
import id.xxx.the.movie.db.data.source.remote.RemoteTvDataSource
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.model.TvResult
import javax.inject.Inject

@ExperimentalPagingApi
class DiscoverTvRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteTvDataSource,
    private val discoverDataSource: DiscoverTvDataSource,
) : DiscoverRemoteMediator<Int, DiscoverTvResultEntity, TvResult>() {

    override suspend fun nextPage() =
        discoverDataSource.getNextPage()

    override suspend fun fetch(page: Int) =
        remoteDataSource.getDiscover(page)

    override suspend fun clearDiscover() =
        discoverDataSource.clearDiscover()

    override suspend fun saveFetch(pageResponse: PageResponse<TvResult>) =
        discoverDataSource.insert(pageResponse)
}