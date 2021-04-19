package id.xxx.the.movie.db.domain.repository

import androidx.paging.PagingData
import id.xxx.base.domain.model.Resource
import id.xxx.the.movie.db.domain.model.DetailMovieModel
import id.xxx.the.movie.db.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovie(id: Int): Flow<Resource<DetailMovieModel>>

    fun getDiscoverMovie(): Flow<PagingData<ItemModel>>
}