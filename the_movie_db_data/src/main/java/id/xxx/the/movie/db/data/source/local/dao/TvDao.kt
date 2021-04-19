package id.xxx.the.movie.db.data.source.local.dao

import androidx.room.Dao
import id.xxx.the.movie.db.data.source.local.dao.discover.tv.DiscoverTv
import id.xxx.the.movie.db.data.source.local.dao.tv.RecommendationTv
import id.xxx.the.movie.db.data.source.local.dao.tv.Tv
import id.xxx.the.movie.db.data.source.local.database.TheMovieDbDatabase

@Dao
abstract class TvDao(val db: TheMovieDbDatabase) : DiscoverTv, Tv, RecommendationTv