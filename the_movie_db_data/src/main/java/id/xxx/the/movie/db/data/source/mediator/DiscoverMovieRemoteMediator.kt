package id.xxx.the.movie.db.data.source.mediator

import androidx.paging.ExperimentalPagingApi
import id.xxx.the.movie.db.data.source.local.DiscoverMovieDataSource
import id.xxx.the.movie.db.data.source.local.entity.discover.movie.DiscoverMovieResultEntity
import id.xxx.the.movie.db.data.source.remote.RemoteMovieDataSource
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.model.MovieResult
import javax.inject.Inject

@ExperimentalPagingApi
class DiscoverMovieRemoteMediator @Inject constructor(
    private val remoteDataSource: RemoteMovieDataSource,
    private val discoverDataSource: DiscoverMovieDataSource,
) : DiscoverRemoteMediator<Int, DiscoverMovieResultEntity, MovieResult>() {

    override suspend fun nextPage() =
        discoverDataSource.getNextPage()

    override suspend fun fetch(page: Int) =
        remoteDataSource.getDiscover(page)

    override suspend fun clearDiscover() =
        discoverDataSource.clearDiscover()

    override suspend fun saveFetch(pageResponse: PageResponse<MovieResult>) =
        discoverDataSource.insert(pageResponse)
}