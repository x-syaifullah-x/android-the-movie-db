package id.xxx.the.movie.db.data.source.local.dao.movie

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import id.xxx.the.movie.db.data.source.local.entity.movie.Movie
import id.xxx.the.movie.db.data.source.local.entity.movie.MovieEntity
import id.xxx.the.movie.db.data.source.local.entity.movie.MovieEntity.Companion.IS_FAVORITE
import id.xxx.the.movie.db.data.source.local.entity.movie.MovieEntity.Companion.PRIMARY_KEY
import id.xxx.the.movie.db.data.source.local.entity.movie.MovieEntity.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

interface Movie : MovieGenre {
    @Transaction
    @Query("SELECT * FROM $TABLE_NAME WHERE $PRIMARY_KEY=:id")
    fun getMovie(id: Int): Flow<Movie?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity): Long

    @Query("UPDATE $TABLE_NAME SET $IS_FAVORITE =:isFavorite WHERE $PRIMARY_KEY=:id")
    suspend fun update(id: Int, isFavorite: Boolean): Int

    @Query("SELECT * FROM $TABLE_NAME WHERE $IS_FAVORITE=1")
    fun getFavorite(): PagingSource<Int, MovieEntity>

    @Query("SELECT DISTINCT $IS_FAVORITE FROM $TABLE_NAME WHERE $PRIMARY_KEY=:id ")
    suspend fun isFavorite(id: Int): Boolean?
}