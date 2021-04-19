package id.xxx.the.movie.db.data.repository

import androidx.paging.*
import id.xxx.base.domain.mediator.flow.NetworkBoundResourceImpl
import id.xxx.base.domain.model.ApiResponse
import id.xxx.the.movie.db.data.mapper.*
import id.xxx.the.movie.db.data.source.local.MovieDataSource
import id.xxx.the.movie.db.data.source.mediator.DiscoverMovieRemoteMediator
import id.xxx.the.movie.db.data.source.remote.RemoteMovieDataSource
import id.xxx.the.movie.db.data.source.remote.response.model.MovieWithRecommendation
import id.xxx.the.movie.db.domain.model.ItemModel
import id.xxx.the.movie.db.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRepositoryImpl @Inject constructor(
    private val remoteMovie: RemoteMovieDataSource,
    private val movie: MovieDataSource,
    private val discoverMovieRemoteMediator: DiscoverMovieRemoteMediator
) : MovieRepository {

    override fun getMovie(id: Int) = NetworkBoundResourceImpl(
        blockResult = { movie.getMovie(id).map { it.toDetailMovieModel() } },
        blockRequest = { remoteMovie.getResponseWithRecommendation<MovieWithRecommendation>(id) },
        blockOnRequest = { apiResponse, b ->
            if (apiResponse is ApiResponse.Success) {
                val it = apiResponse.data
                val movieEntity = it.toMovieEntity()
                val genres = it.genres.map { data -> data.toMovieGenreEntity() }
                val recommendations = it.recommendations.map { data -> data.toPageEmbedded() }
                movie.insert(movieEntity, genres, recommendations)
            }
        }
    ).asFlow()

    override fun getDiscoverMovie(): Flow<PagingData<ItemModel>> = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        remoteMediator = discoverMovieRemoteMediator,
        pagingSourceFactory = { movie.getMovieResult() }
    ).flow.map {
        it.map { data -> data.toItemModel(data.title) }
    }
}