package id.xxx.example.di

import dagger.Component
import id.xxx.base.di.annotation.AppScope
import id.xxx.example.presentation.MainActivity
import id.xxx.the.movie.db.di.dagger.TheMovieDbComponent

@AppScope
@Component(
    dependencies = [TheMovieDbComponent::class],
)

interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(theMovieDbComponent: TheMovieDbComponent): AppComponent
    }

    fun inject(activity: MainActivity)
}