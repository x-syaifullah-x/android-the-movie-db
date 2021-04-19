package id.xxx.the.movie.db.data.source.local.entity.recommendation.movie

import androidx.room.Embedded
import androidx.room.Relation
import id.xxx.the.movie.db.data.source.local.base.IPageEmbed

class RecommendationMovie(
    @Embedded
    private val discover: RecommendationMovieEntity,

    @Relation(
        parentColumn = RecommendationMovieEntity.ID,
        entityColumn = RecommendationMovieResultEntity.FOREIGN_KEY,
        entity = RecommendationMovieResultEntity::class
    )
    private val results: List<RecommendationMovieResultEntity>
) : IPageEmbed<RecommendationMovieResultEntity> {
    override fun page() = discover.page
    override fun totalPages() = discover.totalPages
    override fun totalResult() = discover.totalResults
    override fun results() = results
}