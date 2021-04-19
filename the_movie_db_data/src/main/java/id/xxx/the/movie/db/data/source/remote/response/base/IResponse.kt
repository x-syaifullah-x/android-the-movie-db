package id.xxx.the.movie.db.data.source.remote.response.base

import id.xxx.the.movie.db.data.source.remote.response.model.Genres

interface IResponse {
    val id: Int
    val backdropPath: String?
    val originalLanguage: String
    val overview: String
    val posterPath: String?
    val voteAverage: Float
    val genres: List<Genres>
}