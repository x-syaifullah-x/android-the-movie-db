package id.xxx.the.movie.db.data.source.remote.response.base

import id.xxx.the.movie.db.data.source.remote.response.PageResponse

interface IRecommendation<Result : IResult> {
    val recommendations: List<PageResponse<Result>>
}