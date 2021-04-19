package id.xxx.the.movie.db.data.source.local.dao.tv

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import id.xxx.the.movie.db.data.source.local.entity.tv.Tv
import id.xxx.the.movie.db.data.source.local.entity.tv.TvEntity
import id.xxx.the.movie.db.data.source.local.entity.tv.TvEntity.Companion.IS_FAVORITE
import id.xxx.the.movie.db.data.source.local.entity.tv.TvEntity.Companion.PRIMARY_KEY
import id.xxx.the.movie.db.data.source.local.entity.tv.TvEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

interface Tv : TvGenre {
    @Transaction
    @Query("SELECT * FROM $TABLE_NAME WHERE $PRIMARY_KEY=:id")
    fun getTv(id: Int): Flow<Tv?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvEntity: TvEntity): Long

    @Query("UPDATE $TABLE_NAME SET $IS_FAVORITE =:isFavorite WHERE $PRIMARY_KEY=:id")
    suspend fun update(id: Int, isFavorite: Boolean): Int

    @Query("SELECT * FROM $TABLE_NAME WHERE $IS_FAVORITE=1")
    fun getFavorite(): PagingSource<Int, TvEntity>

    @Query("SELECT DISTINCT $IS_FAVORITE FROM $TABLE_NAME WHERE $PRIMARY_KEY=:id ")
    suspend fun isFavorite(id: Int): Boolean?
}