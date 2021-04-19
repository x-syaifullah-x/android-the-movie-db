package id.xxx.the.movie.db.data.source.remote.response.base

interface IMovieResult : IResult {
    val originalTitle: String?
    val title: String?
}