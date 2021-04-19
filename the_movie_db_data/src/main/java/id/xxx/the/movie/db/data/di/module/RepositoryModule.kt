package id.xxx.the.movie.db.data.di.module

import androidx.paging.ExperimentalPagingApi
import dagger.Binds
import dagger.Module
import id.xxx.the.movie.db.data.di.module.DatabaseModule
import id.xxx.the.movie.db.data.di.module.NetworkModule
import id.xxx.the.movie.db.data.repository.FavoriteRepositoryImpl
import id.xxx.the.movie.db.data.repository.MovieRepositoryImpl
import id.xxx.the.movie.db.data.repository.SearchRepositoryImpl
import id.xxx.the.movie.db.data.repository.TvRepositoryImpl
import id.xxx.the.movie.db.domain.repository.FavoriteRepository
import id.xxx.the.movie.db.domain.repository.MovieRepository
import id.xxx.the.movie.db.domain.repository.SearchRepository
import id.xxx.the.movie.db.domain.repository.TvRepository
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        DatabaseModule::class
    ]
)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    @ExperimentalPagingApi
    abstract fun bindsTvRepositoryImpl(@Singleton repository: TvRepositoryImpl): TvRepository

    @Binds
    @Singleton
    @ExperimentalPagingApi
    abstract fun bindsMovieRepositoryImpl(@Singleton repository: MovieRepositoryImpl): MovieRepository

    @Binds
    @Singleton
    abstract fun bindsSearchRepositoryImpl(@Singleton repository: SearchRepositoryImpl): SearchRepository

    @Binds
    @Singleton
    abstract fun bindsFavoriteRepositoryImpl(@Singleton repository: FavoriteRepositoryImpl): FavoriteRepository
}