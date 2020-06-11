package com.kisaa.www.moviecataloguejetpack.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Mock
    private lateinit var movieRepository: MovieRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getListMovies() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = DataDummy.getListMovies()

        `when`(movieRepository.getListMovies()).thenReturn(movies)

        val listMovies = viewModel.getListMovies().value
        verify(movieRepository).getListMovies()
        assertNotNull(listMovies)
        assertEquals(3, listMovies?.size)

        viewModel.getListMovies().observeForever(observer)
        verify(observer).onChanged(listMovies)
    }
}