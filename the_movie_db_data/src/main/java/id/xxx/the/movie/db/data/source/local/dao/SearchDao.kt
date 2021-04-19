package id.xxx.the.movie.db.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.xxx.the.movie.db.data.source.local.entity.search.SearchEntity
import id.xxx.the.movie.db.data.source.local.entity.search.SearchEntity.Companion.TABLE_NAME
import id.xxx.the.movie.db.data.source.local.entity.search.SearchEntity.Companion.TITLE
import kotlinx.coroutines.flow.Flow

@Dao
abstract class SearchDao {

    @Query("SELECT * FROM $TABLE_NAME WHERE $TITLE  LIKE :query || '%' ORDER BY $TITLE ASC")
    abstract fun search(query: String): Flow<List<SearchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(searchEntity: List<SearchEntity>): List<Long>
}