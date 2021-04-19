package id.xxx.the.movie.db.data.source.local.dao.tv

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import id.xxx.the.movie.db.data.source.local.entity.tv.TvGenreEntity

interface TvGenre {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGenres(list: List<TvGenreEntity>): List<Long>
}