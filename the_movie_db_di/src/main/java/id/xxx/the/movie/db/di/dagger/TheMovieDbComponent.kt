package id.xxx.the.movie.db.di.dagger

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import id.xxx.the.movie.db.data.di.component.DataComponent
import id.xxx.the.movie.db.domain.di.component.DomainComponent
import id.xxx.the.movie.db.domain.usecase.Interactor
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataComponent::class,
        DomainComponent::class
    ]
)
interface TheMovieDbComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): TheMovieDbComponent
    }

    @Suppress("SpellCheckingInspection")
    fun provideInteractor(): Interactor
}