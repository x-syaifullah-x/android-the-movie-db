package id.xxx.the.movie.db.data.source.local.base

interface IMovieResultEntity : IResultEntity {
    val foreignKey: Int
    val originalTitle: String?
    val title: String?
}