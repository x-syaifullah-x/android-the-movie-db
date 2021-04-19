package id.xxx.the.movie.db.data.source.local.base

interface ITvResultEntity : IResultEntity {
    val foreignKey: Int
    val originalName: String
    val name: String
}