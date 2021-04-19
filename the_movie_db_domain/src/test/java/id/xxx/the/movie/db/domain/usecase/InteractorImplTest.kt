package id.xxx.the.movie.db.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import id.xxx.test.rule.RuleUnitTestWithCoroutine
import id.xxx.the.movie.db.domain.model.ItemModel
import id.xxx.the.movie.db.domain.repository.FavoriteRepository
import id.xxx.the.movie.db.domain.repository.MovieRepository
import id.xxx.the.movie.db.domain.repository.SearchRepository
import id.xxx.the.movie.db.domain.repository.TvRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InteractorImplTest : RuleUnitTestWithCoroutine() {

    private lateinit var interactorImpl: InteractorImpl

    @Mock
    lateinit var tvRepository: TvRepository

    @Mock
    lateinit var movieRepository: MovieRepository

    @Mock
    lateinit var favoriteRepository: FavoriteRepository

    @Mock
    lateinit var searchRepository: SearchRepository

    override fun before() {
        interactorImpl =
            InteractorImpl(tvRepository, movieRepository, favoriteRepository, searchRepository)
    }

    @Test
    fun test() = runBlocking {
        Mockito.`when`(tvRepository.getDiscoverTv()).thenReturn(
            flowOf(
                PagingData.from(
                    listOf(
                        ItemModel("", "", "", 1f, 10)
                    )
                )
            )
        )
        val data = interactorImpl.getDiscoverTv()

        data.collectLatest {
            Assert.assertNotNull(it)
            it.map {
                println("aaasassasas")
            }
        }
    }
}