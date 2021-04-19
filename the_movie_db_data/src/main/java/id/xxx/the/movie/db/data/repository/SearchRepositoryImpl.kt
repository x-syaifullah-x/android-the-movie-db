package id.xxx.the.movie.db.data.repository

import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import id.xxx.base.domain.model.ApiResponse
import id.xxx.base.domain.model.Resource
import id.xxx.the.movie.db.data.mapper.toSearchEntity
import id.xxx.the.movie.db.data.mapper.toSearchModel
import id.xxx.the.movie.db.data.source.local.SearchDataSource
import id.xxx.the.movie.db.data.source.remote.RemoteSearchDataSource
import id.xxx.the.movie.db.domain.model.SearchModel
import id.xxx.the.movie.db.domain.repository.SearchRepository
import id.xxx.the.movie.db.domain.repository.SearchRepository.Companion.MEDIA_TYPE_MOVIE
import id.xxx.the.movie.db.domain.repository.SearchRepository.Companion.MEDIA_TYPE_TV
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteSearchDataSource: RemoteSearchDataSource,
    private val searchDataSource: SearchDataSource
) : SearchRepository {

    override fun search(query: String): Flow<Resource<List<SearchModel>>> {
        return NetworkBoundResourceImpl(
            blockResult = {
                searchDataSource.search(query)
                    .map { it.map { data -> data.toSearchModel() } }
            },
            blockRequest = { remoteSearchDataSource.search(query) },
            blockOnRequest = { apiResponse, b ->
                if (apiResponse is ApiResponse.Success) {
                    val it = apiResponse.data
                    val searchEntity = it.results
                        .filter { type -> type.mediaType == MEDIA_TYPE_MOVIE || type.mediaType == MEDIA_TYPE_TV }
                        .map { data -> data.toSearchEntity() }
                    searchDataSource.insert(searchEntity)
                }
            }
        ).asFlow()
    }
}