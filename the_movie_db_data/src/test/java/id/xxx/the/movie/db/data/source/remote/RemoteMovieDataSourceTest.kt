package id.xxx.the.movie.db.data.source.remote

import id.xxx.base.domain.model.ApiResponse
import id.xxx.test.rule.RuleUnitTestWithCoroutine
import id.xxx.the.movie.db.data.source.remote.network.services.MovieApiService
import id.xxx.the.movie.db.data.source.remote.response.MovieResponse
import id.xxx.the.movie.db.data.source.remote.response.PageResponse
import id.xxx.the.movie.db.data.source.remote.response.model.MovieResult
import id.xxx.the.movie.db.data.source.remote.response.model.MovieWithRecommendation
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
class RemoteMovieDataSourceTest : RuleUnitTestWithCoroutine() {

    companion object {
        private const val FAKE_ID = 1010

        private fun pageResponse(list: MovieResult? = null): PageResponse<MovieResult> {
            return PageResponse(1, 10, 10, listOf(movieResult))
        }

        private val movieResult = MovieResult(
            id = 1,
            backdropPath = "bp",
            originalTitle = "ot",
            posterPath = "pp",
            voteAverage = 1f,
            overview = "o",
            title = "t",
            originalLanguage = "ol"
        )

        private val movieResponse = MovieResponse(
            "bp",
            listOf(),
            1,
            "ol",
            "o", "pp",
            1f,
            "ot",
            "rd",
            "t"
        )
    }

    private lateinit var dataSource: RemoteMovieDataSource

    @Mock
    lateinit var apiService: MovieApiService

    override fun before() {
        dataSource = RemoteMovieDataSource(apiService)
    }

    @Test
    fun getDiscoverResultSuccess() = runBlocking {
        Mockito.`when`(apiService.getDiscoverMovie(page = 1))
            .thenReturn(pageResponse(movieResult))
        val result = dataSource.getDiscover(1)
        Assert.assertTrue(result is ApiResponse.Success)
    }

    @Test
    fun getDiscoverResultEmpty() = runBlocking {
        Mockito.`when`(apiService.getDiscoverMovie(page = 1))
            .thenReturn(null)
        val result = dataSource.getDiscover(1)
        Assert.assertTrue(result is ApiResponse.Empty)
    }

    @Test
    fun getDiscoverResultError() = runBlocking {
        Mockito.`when`(apiService.getDiscoverMovie(page = 1)).thenThrow(MockitoException("test"))
        val result = dataSource.getDiscover(1)
        Assert.assertTrue(result is ApiResponse.Error)
    }

    @Test
    fun getMovieWithMovieRecommendationResultSuccess() = runBlocking {

        Mockito.`when`(apiService.getMovie(id = FAKE_ID))
            .thenReturn(movieResponse)

        Mockito.`when`(apiService.getRecommendationMovie(id = FAKE_ID, 1))
            .thenReturn(pageResponse())

        dataSource.getResponseWithRecommendation<MovieWithRecommendation>(FAKE_ID)
            .collectLatest { response ->
                Assert.assertTrue(response is ApiResponse.Success)
                if (response is ApiResponse.Success) {
//                    val dataFake = FakeDataMovie.movieWithRecommendation
//                    val data = response.data
//                    Assert.assertEquals(dataFake, data)
//                    Assert.assertEquals(dataFake.recommendations, data.recommendations)
                }
            }
    }

    @Test
    fun getMovieWithMovieRecommendationResultEmpty(): Unit = runBlocking {

        Mockito.`when`(apiService.getMovie(id = FAKE_ID))
            .thenReturn(null)

        Mockito.`when`(apiService.getRecommendationMovie(id = FAKE_ID, 1))
            .thenReturn(pageResponse())

        dataSource.getResponseWithRecommendation<MovieWithRecommendation>(FAKE_ID)
            .apply {
                collectLatest {
                    Assert.assertEquals(ApiResponse.Empty, it)
                }
            }
    }

    @Test
    fun getMovieWithMovieRecommendationResultError(): Unit = runBlocking {

        Mockito.`when`(apiService.getMovie(id = FAKE_ID))
            .thenThrow(MockitoException("test error"))

        Mockito.`when`(apiService.getRecommendationMovie(id = FAKE_ID, 1))
            .thenReturn(pageResponse())

        val results =
            dataSource.getResponseWithRecommendation<MovieWithRecommendation>(FAKE_ID)

        results.collectLatest {
//            Assert.assertEquals(ApiResponse.Error(MockitoException("test error")), it)
        }
    }
}