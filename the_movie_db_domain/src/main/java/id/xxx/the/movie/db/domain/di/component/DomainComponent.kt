package id.xxx.the.movie.db.domain.di.component

import dagger.Module
import id.xxx.the.movie.db.domain.di.module.UseCaseModule

@Module(
    includes = [
        UseCaseModule::class
    ]
)
abstract class DomainComponent