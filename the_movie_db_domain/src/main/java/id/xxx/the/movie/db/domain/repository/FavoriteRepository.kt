package id.xxx.the.movie.db.domain.repository

import androidx.paging.PagingData
import id.xxx.the.movie.db.domain.model.ItemModel
import id.xxx.the.movie.db.domain.model.Type
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getMovieFavorite(): Flow<PagingData<ItemModel>>

    fun getTvFavorite(): Flow<PagingData<ItemModel>>

    suspend fun setFavorite(type: Type<*>, isFavorite: Boolean): Int
}