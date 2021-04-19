package id.xxx.the.movie.db.domain.model

import id.xxx.the.movie.db.domain.model.base.IModel

data class ItemModel(
    val name: String,
    val overview: String,
    val urlPosterPath: String,
    val voteAverage: Float,
    override val id: Int
) : IModel<Int>