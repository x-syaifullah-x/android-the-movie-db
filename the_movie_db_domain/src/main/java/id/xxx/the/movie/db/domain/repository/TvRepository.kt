package id.xxx.the.movie.db.domain.repository

import androidx.paging.PagingData
import id.xxx.base.domain.model.Resource
import id.xxx.the.movie.db.domain.model.DetailTvModel
import id.xxx.the.movie.db.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface TvRepository {

    fun getDiscoverTv(): Flow<PagingData<ItemModel>>

    fun getTv(id: Int): Flow<Resource<DetailTvModel>>

}