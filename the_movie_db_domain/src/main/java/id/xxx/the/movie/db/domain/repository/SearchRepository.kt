package id.xxx.the.movie.db.domain.repository

import id.xxx.base.domain.model.Resource
import id.xxx.the.movie.db.domain.model.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    companion object {
        const val MEDIA_TYPE_MOVIE = "movie"
        const val MEDIA_TYPE_TV = "tv"
    }

    fun search(query: String): Flow<Resource<List<SearchModel>>>
}