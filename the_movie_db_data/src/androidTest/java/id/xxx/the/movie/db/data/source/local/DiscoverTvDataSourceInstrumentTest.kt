package id.xxx.the.movie.db.data.source.local

import id.xxx.the.movie.db.data.source.local.database.TheMovieDbDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DiscoverTvDataSourceInstrumentTest {

    private lateinit var dataSource: DiscoverTvDataSource

    private lateinit var db: TheMovieDbDatabase

    @Test
    fun insertResultEmptyTest() = runBlocking {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(context, TheMovieDbDatabase::class.java).build()
//
//        dataSource = DiscoverTvDataSource(db.tvDao())
//        val results = dataSource.insert(Data.fakePageResponse(listOf()))
//        Assert.assertTrue(results)
    }

    @Test
    fun insertResultNotEmptyTest() = runBlocking {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(context, TheMovieDbDatabase::class.java).build()
//
//        dataSource = DiscoverTvDataSource(db.tvDao())
//        val results = dataSource.insert(Data.fakePageResponse(listOf(Data.fakeTvResult())))
//        Assert.assertTrue(results)
    }
}