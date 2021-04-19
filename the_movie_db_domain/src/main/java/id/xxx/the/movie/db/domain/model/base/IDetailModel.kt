package id.xxx.the.movie.db.domain.model.base

import id.xxx.the.movie.db.domain.model.GenreModel
import id.xxx.the.movie.db.domain.model.ItemModel

interface IDetailModel : IModel<Int> {
    val backdropPath: String
    val originalLanguage: String
    val overview: String
    val urlPosterPath: String
    val title: String
    val voteAverage: Float
    val genres: List<GenreModel>
    val recommendation: List<ItemModel>
    val isFavorite: Boolean
}