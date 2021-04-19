package id.xxx.the.movie.db.data.source.remote

import id.xxx.base.domain.model.ApiResponse
import id.xxx.test.rule.RuleUnitTestWithCoroutine
import id.xxx.the.movie.db.data.source.remote.network.services.SearchApiService
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.model.MultiResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteSearchDataSourceTest : RuleUnitTestWithCoroutine() {

    companion object {
        const val FAKE_QUERY = "test"
        private val pageResponse = PageResponse<MultiResult>(1, 10, 10, listOf())
    }

    private lateinit var dataSource: RemoteSearchDataSource

    @Mock
    lateinit var apiService: SearchApiService

    override fun before() {
        dataSource = RemoteSearchDataSource(apiService)
    }

    @Test
    fun searchQueryEmpty(): Unit = runBlocking {
        dataSource.search("").apply {
            collectLatest {
                Assert.assertTrue(it is ApiResponse.Empty)
            }
        }
    }

    @Test
    fun searchResultEmpty(): Unit = runBlocking {
        Mockito.`when`(apiService.searchMulti(FAKE_QUERY))
            .thenReturn(pageResponse)
        dataSource.search(FAKE_QUERY).apply {
            collectLatest {
                Assert.assertTrue(it is ApiResponse.Empty)
            }
        }
    }

    @Test
    fun searchResultSuccess(): Unit = runBlocking {
        Mockito.`when`(apiService.searchMulti(FAKE_QUERY))
            .thenReturn(pageResponse)
        dataSource.search(FAKE_QUERY).apply {
            collectLatest {
//                Assert.assertTrue(it is ApiResponse.Success)
            }
        }
    }

    @Test
    fun searchResultError(): Unit = runBlocking {
        Mockito.`when`(apiService.searchMulti(FAKE_QUERY))
            .thenThrow(MockitoException("test error"))
        dataSource.search(FAKE_QUERY).apply {
            collectLatest {
                Assert.assertTrue(it is ApiResponse.Error)
            }
        }
    }
}