package id.xxx.the.movie.db.data.source.local

import id.xxx.the.movie.db.data.source.local.base.IEntity
import id.xxx.the.movie.db.data.source.local.base.IPageEmbed
import id.xxx.the.movie.db.data.source.local.dao.TvDao
import id.xxx.the.movie.db.data.source.local.entity.recommendation.tv.RecommendationTvEntity
import id.xxx.the.movie.db.data.source.local.entity.recommendation.tv.RecommendationTvResultEntity
import id.xxx.the.movie.db.data.source.local.entity.tv.TvEntity
import id.xxx.the.movie.db.data.source.local.entity.tv.TvGenreEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

class TvDataSource @Inject constructor(private val dao: TvDao) {

    fun getTvResult() = dao.getResult()

    fun getTv(id: Int) = dao.getTv(id)

    suspend fun setFavorite(id: Int, isFavorite: Boolean) = dao.update(id, isFavorite)

    fun getFavorite() = dao.getFavorite()

    suspend fun insert(
        tv: TvEntity,
        genres: List<TvGenreEntity>,
        recommendations: List<IPageEmbed<RecommendationTvResultEntity>>
    ) = supervisorScope {
        val isFavorite = dao.isFavorite(tv.primaryKey) ?: false
        val newData = tv.copy(isFavorite = isFavorite)
        val foreignKey = dao.insert(newData)

        if (IEntity.successInsert(foreignKey)) {
            launch { dao.insertGenres(genres.map { it.copy(foreignKey = foreignKey) }) }
            launch { insertRecommendation(foreignKey, recommendations) }
        }
    }

    private suspend fun insertRecommendation(
        foreignKey: Long, pageEmbedded: List<IPageEmbed<RecommendationTvResultEntity>>
    ) = supervisorScope {
        pageEmbedded.forEach {
            launch {
                val recommendationTvEntity = RecommendationTvEntity(
                    id = foreignKey,
                    page = it.page(),
                    totalResults = it.totalResult(),
                    totalPages = it.totalPages()
                )
                dao.insert(recommendationTvEntity)
            }
            launch {
                val results = it.results().map { it.copy(foreignKey = foreignKey.toInt()) }
                dao.insertRecommendations(results)
            }
        }
    }
}