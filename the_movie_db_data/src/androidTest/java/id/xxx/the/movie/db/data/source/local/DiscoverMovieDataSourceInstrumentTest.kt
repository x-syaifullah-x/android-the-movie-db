package id.xxx.the.movie.db.data.source.local

import id.xxx.the.movie.db.data.source.local.database.TheMovieDbDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DiscoverMovieDataSourceInstrumentTest {

    private lateinit var dataSource: DiscoverMovieDataSource

    private lateinit var db: TheMovieDbDatabase

    @Test
    fun insertResultEmptyTest() = runBlocking {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(context, TheMovieDbDatabase::class.java).build()
//
//        dataSource = DiscoverMovieDataSource(db.movieDao())
//        val results = dataSource.insert(Data.fakePageResponse(listOf()))
//        Assert.assertTrue(results)
    }

    @Test
    fun insertResultNotEmptyTest() = runBlocking {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(context, TheMovieDbDatabase::class.java).build()
//
//        dataSource = DiscoverMovieDataSource(db.movieDao())
//        val results = dataSource.insert(Data.fakePageResponse(listOf(Data.fakeMovieResult())))
//        Assert.assertTrue(results)
    }
}