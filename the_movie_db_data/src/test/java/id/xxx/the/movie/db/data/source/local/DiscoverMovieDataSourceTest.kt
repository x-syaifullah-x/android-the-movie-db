package id.xxx.the.movie.db.data.source.local

import id.xxx.test.rule.RuleUnitTestWithCoroutine
import id.xxx.the.movie.db.data.source.local.dao.MovieDao
import id.xxx.the.movie.db.data.source.local.entity.discover.movie.DiscoverMovieEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiscoverMovieDataSourceTest : RuleUnitTestWithCoroutine() {

    private fun getDiscoverMovieEntity(a: Int): DiscoverMovieEntity? {
        return null
    }

    private lateinit var dataSource: DiscoverMovieDataSource

    @Mock
    lateinit var dao: MovieDao

    override fun before() {
        dataSource = DiscoverMovieDataSource(dao)
    }

    @Test
    fun clearDiscoverSuccessTest(): Unit = runBlocking {
        val successClear = 1
        Mockito.`when`(dao.clearDiscoverMovie()).thenReturn(successClear)
        val results = dataSource.clearDiscover()
        Assert.assertTrue(results == successClear)
    }

    @Test
    fun clearDiscoverFailTest(): Unit = runBlocking {
        val successClear = 0
        Mockito.`when`(dao.clearDiscoverMovie()).thenReturn(successClear)
        val results = dataSource.clearDiscover()
        Assert.assertTrue(results == successClear)
    }

    @Test
    fun getPreviousPageIsNullTest(): Unit = runBlocking {
        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverMovieEntity(1))
        val results = dataSource.getPreviousPage()
        Assert.assertTrue(results == null)
    }

    @Test
    fun getPreviousPageIsReadyTest(): Unit = runBlocking {
//        val totalPage = 10
//        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverMovieEntity(totalPage))
//        val results = dataSource.getPreviousPage()
//        Assert.assertTrue(results != null)
//        Assert.assertTrue((results == totalPage - 1))
    }

    @Test
    fun getCurrentPageIsNullTest(): Unit = runBlocking {
        Mockito.`when`(dao.getLastPage()).thenReturn(null)
        val results = dataSource.getCurrentPage()
        Assert.assertTrue(results == null)
    }

    @Test
    fun getCurrentPageIsNotNullTest(): Unit = runBlocking {
//        val totalPage = 1
//        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverMovieEntity(totalPage))
//
//        val results = dataSource.getCurrentPage()
//        Assert.assertTrue(results != null)
//        Assert.assertTrue(results?.totalPages == totalPage)
    }

    @Test
    fun getNextPageIsNotNullTest(): Unit = runBlocking {
//        val totalPage = 2
//        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverMovieEntity(totalPage))
//        val results = dataSource.getNextPage()
//        Assert.assertTrue(results == totalPage + 1)
    }

    @Test
    fun getNextPageIsNullTest(): Unit = runBlocking {
        val totalPage = 1
        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverMovieEntity(totalPage))
        val results = dataSource.getNextPage()
        Assert.assertTrue(results == null)
    }
}