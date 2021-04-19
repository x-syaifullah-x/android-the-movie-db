package id.xxx.the.movie.db.data.source.local

import androidx.room.withTransaction
import id.xxx.the.movie.db.data.mapper.toDiscoverTvResultEntity
import id.xxx.the.movie.db.data.source.local.base.IDiscoverDataSource
import id.xxx.the.movie.db.data.source.local.dao.TvDao
import id.xxx.the.movie.db.data.source.local.entity.discover.tv.DiscoverTvEntity
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.model.TvResult
import javax.inject.Inject

class DiscoverTvDataSource @Inject constructor(
    private val dao: TvDao
) : IDiscoverDataSource<DiscoverTvEntity, TvResult> {
    override suspend fun clearDiscover() = dao.clearDiscoverMovie()

    override suspend fun getPreviousPage() = dao.getLastPage()?.run {
        if (page == 1) null else page.minus(1)
    }

    override suspend fun getCurrentPage() = dao.getLastPage()

    override suspend fun getNextPage() = dao.getLastPage()?.run {
        if (page == totalPages) null else page.plus(1)
    }

    override suspend fun insert(data: PageResponse<TvResult>) = dao.db.withTransaction {
        val discover = DiscoverTvEntity(
            page = data.page, totalPages = data.totalPages, totalResults = data.totalResults
        )
        val resultDiscover = dao.insert(discover).toInt() != 0

        val results = data.results
        if (results.isNotEmpty()) dao.insertDiscoverResults(
            results.map { it.toDiscoverTvResultEntity(data.page) }
        )
        return@withTransaction resultDiscover
    }
}