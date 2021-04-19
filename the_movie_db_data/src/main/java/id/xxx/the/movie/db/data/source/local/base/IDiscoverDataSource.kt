package id.xxx.the.movie.db.data.source.local.base

import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.base.IResult

interface IDiscoverDataSource<DiscoverEntity, ResultResponse : IResult> {

    suspend fun clearDiscover(): Int

    suspend fun getPreviousPage(): Int?

    suspend fun getCurrentPage(): DiscoverEntity?

    suspend fun getNextPage(): Int?

    suspend fun insert(data: PageResponse<ResultResponse>): Boolean
}