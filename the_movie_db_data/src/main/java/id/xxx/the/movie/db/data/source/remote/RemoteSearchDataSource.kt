package id.xxx.the.movie.db.data.source.remote

import id.xxx.base.domain.model.getApiResponseAsFlow
import id.xxx.the.movie.db.data.source.remote.network.services.SearchApiService
import javax.inject.Inject

class RemoteSearchDataSource @Inject constructor(private val apiService: SearchApiService) {

    fun search(query: String) = getApiResponseAsFlow(
        blockFirst = { query.isNotEmpty() },
        blockFetch = { apiService.searchMulti(query) },
        blockOnFetch = { it.results.isNotEmpty() },
    )
}