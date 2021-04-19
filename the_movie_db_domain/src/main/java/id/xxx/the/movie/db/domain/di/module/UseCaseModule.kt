package id.xxx.the.movie.db.domain.di.module

import dagger.Binds
import dagger.Module
import id.xxx.the.movie.db.domain.usecase.Interactor
import id.xxx.the.movie.db.domain.usecase.InteractorImpl
import javax.inject.Singleton

@Module
internal abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindsInteractImpl(@Singleton interact: InteractorImpl): Interactor
}