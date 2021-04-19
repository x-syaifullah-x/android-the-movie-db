package id.xxx.the.movie.db.data.source.local

import androidx.room.withTransaction
import id.xxx.the.movie.db.data.mapper.toDiscoverMovieResultEntity
import id.xxx.the.movie.db.data.source.local.base.IDiscoverDataSource
import id.xxx.the.movie.db.data.source.local.dao.MovieDao
import id.xxx.the.movie.db.data.source.local.entity.discover.movie.DiscoverMovieEntity
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.model.MovieResult
import javax.inject.Inject

class DiscoverMovieDataSource @Inject constructor(
    private val dao: MovieDao
) : IDiscoverDataSource<DiscoverMovieEntity, MovieResult> {
    override suspend fun clearDiscover() = dao.clearDiscoverMovie()

    override suspend fun getPreviousPage() = dao.getLastPage()?.run {
        if (page == 1) null else page.minus(1)
    }

    override suspend fun getCurrentPage() = dao.getLastPage()

    override suspend fun getNextPage() = dao.getLastPage()?.run {
        if (page == totalPages) null else page.plus(1)
    }

    override suspend fun insert(data: PageResponse<MovieResult>) = dao.db.withTransaction {
        val discover = DiscoverMovieEntity(
            page = data.page, totalPages = data.totalPages, totalResults = data.totalResults
        )
        val resultDiscover = dao.insert(discover).toInt() != 0

        val results = data.results
        if (results.isNotEmpty()) dao.insertDiscoverResults(
            results.map { it.toDiscoverMovieResultEntity(data.page) }
        )
        return@withTransaction resultDiscover
    }
}