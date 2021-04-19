package id.xxx.the.movie.db.data.source.local

import id.xxx.test.rule.RuleUnitTestWithCoroutine
import id.xxx.the.movie.db.data.source.local.dao.TvDao
import id.xxx.the.movie.db.data.source.local.entity.discover.tv.DiscoverTvEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiscoverTvDataSourceTest : RuleUnitTestWithCoroutine() {

    private fun getDiscoverTvEntity(a: Int): DiscoverTvEntity? {
        return null
    }

    private lateinit var dataSource: DiscoverTvDataSource

    @Mock
    lateinit var dao: TvDao

    override fun before() {
        dataSource = DiscoverTvDataSource(dao)
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
        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverTvEntity(1))
        val results = dataSource.getPreviousPage()
        Assert.assertTrue(results == null)
    }

    @Test
    fun getPreviousPageIsReadyTest(): Unit = runBlocking {
//        val totalPage = 10
//        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverTvEntity(totalPage))
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
//        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverTvEntity(totalPage))
//
//        val results = dataSource.getCurrentPage()
//        Assert.assertTrue(results != null)
//        Assert.assertTrue(results?.totalPages == totalPage)
    }

    @Test
    fun getNextPageIsNotNullTest(): Unit = runBlocking {
//        val totalPage = 2
//        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverTvEntity(totalPage))
//        val results = dataSource.getNextPage()
//        Assert.assertTrue(results == totalPage + 1)
    }

    @Test
    fun getNextPageIsNullTest(): Unit = runBlocking {
        val totalPage = 1
        Mockito.`when`(dao.getLastPage()).thenReturn(getDiscoverTvEntity(totalPage))
        val results = dataSource.getNextPage()
        Assert.assertTrue(results == null)
    }
}