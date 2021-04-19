package id.xxx.the.movie.db.data.source.local.dao

import androidx.room.Dao
import id.xxx.the.movie.db.data.source.local.dao.discover.movie.DiscoverMovie
import id.xxx.the.movie.db.data.source.local.dao.movie.Movie
import id.xxx.the.movie.db.data.source.local.dao.movie.RecommendationMovie
import id.xxx.the.movie.db.data.source.local.database.TheMovieDbDatabase

@Dao
abstract class MovieDao(val db: TheMovieDbDatabase) : DiscoverMovie, Movie, RecommendationMovie