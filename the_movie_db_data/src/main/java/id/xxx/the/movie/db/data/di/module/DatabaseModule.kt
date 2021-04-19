package id.xxx.the.movie.db.data.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import id.xxx.the.movie.db.data.source.local.database.TheMovieDbDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
internal class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, TheMovieDbDatabase::class.java, "the-movie-db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .openHelperFactory(SupportFactory(SQLiteDatabase.getBytes("passphrase".toCharArray())))
            .build()

    @Singleton
    @Provides
    fun provideMovieDao(@Singleton database: TheMovieDbDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun provideTvDao(@Singleton database: TheMovieDbDatabase) = database.tvDao()

    @Singleton
    @Provides
    fun provideSearchDao(@Singleton database: TheMovieDbDatabase) = database.searchDao()
}