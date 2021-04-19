package id.xxx.the.movie.db.data.source.local.entity.discover.tv

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.xxx.the.movie.db.data.source.local.base.IPage
import id.xxx.the.movie.db.data.source.local.entity.discover.tv.DiscoverTvEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
class DiscoverTvEntity(
    @ColumnInfo(name = PAGE)
    @PrimaryKey
    override val page: Int,
    override val totalResults: Int,
    override val totalPages: Int,
) : IPage {
    companion object {
        const val PAGE = "page"
        const val TABLE_NAME = "discover_tv"
    }
}