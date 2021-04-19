package id.xxx.example

import android.app.Application
import id.xxx.example.di.AppComponent
import id.xxx.example.di.DaggerAppComponent
import id.xxx.the.movie.db.di.dagger.DaggerTheMovieDbComponent
import id.xxx.the.movie.db.di.dagger.TheMovieDbComponent

class App : Application() {

    val theMovieDbComponent: TheMovieDbComponent by lazy {
        DaggerTheMovieDbComponent.factory().create(this)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(theMovieDbComponent)
    }
}