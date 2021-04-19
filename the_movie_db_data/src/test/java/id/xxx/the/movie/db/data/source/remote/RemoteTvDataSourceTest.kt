package id.xxx.the.movie.db.data.source.remote

import id.xxx.base.domain.model.ApiResponse
import id.xxx.test.rule.RuleUnitTestWithCoroutine
import id.xxx.the.movie.db.data.source.remote.network.services.TvApiService
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.TvResponse
import id.xxx.the.movie.db.data.source.remote.response.model.TvResult
import id.xxx.the.movie.db.data.source.remote.response.model.TvWithTvRecommendation
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
class RemoteTvDataSourceTest : RuleUnitTestWithCoroutine() {

    companion object {
        private const val FAKE_ID = 1010
        private val tvResult = TvResult(
            originalLanguage = "ol",
            overview = "o",
            voteAverage = 1f,
            posterPath = "pp",
            backdropPath = "bp",
            id = FAKE_ID,
            name = "n",
            originalName = "on"
        )
        private val pageResponse = PageResponse<TvResult>(1, 10, 1, listOf())
        private val pageResponseResultNotEmpty = PageResponse(1, 10, 1, listOf(tvResult))
        private val movieResponse = TvResponse(
            "bp",
            listOf(),
            1,
            "ol",
            "o", "pp",
            1f,
            "ot",
            "rd",
            "t",
            "on"
        )
    }

    private lateinit var dataSource: RemoteTvDataSource

    @Mock
    lateinit var apiService: TvApiService

    override fun before() {
        dataSource = RemoteTvDataSource(apiService)
    }

    @Test
    fun getDiscoverResultSuccess() = runBlocking {
        Mockito.`when`(apiService.getDiscoverTv(page = 1))
            .thenReturn(pageResponseResultNotEmpty)
        val result = dataSource.getDiscover(1)
        Assert.assertTrue(result is ApiResponse.Success)
    }

    @Test
    fun getDiscoverResultEmpty() = runBlocking {
        Mockito.`when`(apiService.getDiscoverTv(page = 1))
            .thenReturn(pageResponse)
        val result = dataSource.getDiscover(1)
        Assert.assertTrue(result is ApiResponse.Empty)
    }

    @Test
    fun getDiscoverResultError() = runBlocking {
        Mockito.`when`(apiService.getDiscoverTv(page = 1)).thenThrow(MockitoException("test error"))
        val result = dataSource.getDiscover(1)
        Assert.assertTrue(result is ApiResponse.Error)
    }

    @Test
    fun getTvWithTvRecommendationResultSuccess() = runBlocking {
        Mockito.`when`(apiService.getTv(id = FAKE_ID))
            .thenReturn(movieResponse)

        Mockito.`when`(apiService.getRecommendationTv(id = FAKE_ID, 1))
            .thenReturn(pageResponse)

        dataSource.getResponseWithRecommendation<TvWithTvRecommendation>(FAKE_ID)
            .collectLatest { response ->
                Assert.assertTrue(response is ApiResponse.Success)
                if (response is ApiResponse.Success) {
//                    val dataFake = FakeDataTv.tvWithRecommendation
//                    val data = response.data
//                    Assert.assertEquals(dataFake, data)
//                    Assert.assertEquals(dataFake.recommendations, data.recommendations)
                }
            }
    }

    @Test
    fun getTvWithTvRecommendationResultEmpty(): Unit = runBlocking {
        Mockito.`when`(apiService.getTv(id = FAKE_ID))
            .thenReturn(null)

        Mockito.`when`(apiService.getRecommendationTv(id = FAKE_ID, 1))
            .thenReturn(pageResponse)

        dataSource.getResponseWithRecommendation<TvWithTvRecommendation>(FAKE_ID).apply {
            collectLatest {
                Assert.assertEquals(ApiResponse.Empty, it)
            }
        }
    }

    @Test
    fun getTvWithTvRecommendationResultError(): Unit = runBlocking {
        Mockito.`when`(apiService.getTv(id = FAKE_ID))
            .thenThrow(MockitoException("test error"))

        Mockito.`when`(apiService.getRecommendationTv(id = FAKE_ID, 1))
            .thenReturn(pageResponse)

        val results = dataSource.getResponseWithRecommendation<TvWithTvRecommendation>(FAKE_ID)

        results.collectLatest {
//            Assert.assertEquals(ApiResponse.Error(MockitoException("test error")), it)
        }
    }
}