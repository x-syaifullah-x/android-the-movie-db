package id.xxx.the.movie.db.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.xxx.the.movie.db.data.source.local.dao.MovieDao
import id.xxx.the.movie.db.data.source.local.dao.SearchDao
import id.xxx.the.movie.db.data.source.local.dao.TvDao
import id.xxx.the.movie.db.data.source.local.entity.discover.movie.DiscoverMovieEntity
import id.xxx.the.movie.db.data.source.local.entity.discover.movie.DiscoverMovieResultEntity
import id.xxx.the.movie.db.data.source.local.entity.discover.tv.DiscoverTvEntity
import id.xxx.the.movie.db.data.source.local.entity.discover.tv.DiscoverTvResultEntity
import id.xxx.the.movie.db.data.source.local.entity.movie.MovieEntity
import id.xxx.the.movie.db.data.source.local.entity.movie.MovieGenreEntity
import id.xxx.the.movie.db.data.source.local.entity.recommendation.movie.RecommendationMovieEntity
import id.xxx.the.movie.db.data.source.local.entity.recommendation.movie.RecommendationMovieResultEntity
import id.xxx.the.movie.db.data.source.local.entity.recommendation.tv.RecommendationTvEntity
import id.xxx.the.movie.db.data.source.local.entity.recommendation.tv.RecommendationTvResultEntity
import id.xxx.the.movie.db.data.source.local.entity.search.SearchEntity
import id.xxx.the.movie.db.data.source.local.entity.tv.TvEntity
import id.xxx.the.movie.db.data.source.local.entity.tv.TvGenreEntity

@Database(
    entities = [
        DiscoverMovieEntity::class,
        DiscoverMovieResultEntity::class,
        DiscoverTvEntity::class,
        DiscoverTvResultEntity::class,
        MovieGenreEntity::class,
        TvGenreEntity::class,
        MovieEntity::class,
        TvEntity::class,
        RecommendationMovieEntity::class,
        RecommendationTvEntity::class,
        RecommendationMovieResultEntity::class,
        RecommendationTvResultEntity::class,
        SearchEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class TheMovieDbDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun tvDao(): TvDao

    abstract fun searchDao(): SearchDao
}