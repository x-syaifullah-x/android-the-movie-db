package id.xxx.the.movie.db.data.di.component

import dagger.Module
import id.xxx.the.movie.db.data.di.module.RepositoryModule

@Module(
    includes = [
        RepositoryModule::class
    ]
)
abstract class DataComponent