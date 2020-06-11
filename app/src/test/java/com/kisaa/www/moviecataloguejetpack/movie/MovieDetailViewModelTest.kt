package com.kisaa.www.moviecataloguejetpack.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieDetailViewModelTest {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var movieEntity: MovieEntity
    private lateinit var favoriteEntity: FavoriteEntity

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<MovieEntity>

    @Mock
    private lateinit var observerFav: Observer<FavoriteEntity>

    @Before
    fun setUp() {
        viewModel = MovieDetailViewModel(movieRepository)
        movieEntity = MovieEntity()
        favoriteEntity = DataDummy.getFavoriteMovie("419704")!!
    }

    @Test
    fun getDetailMovie() {
        val dataMovie = MutableLiveData<MovieEntity>()
        dataMovie.value = DataDummy.getMovie(movieEntity.id)

        `when`(movieRepository.getDetailMovie(movieEntity.id)).thenReturn(dataMovie)
        val movie = viewModel.getDetailMovie(movieEntity.id).value
        verify(movieRepository).getDetailMovie(movieEntity.id)
        assertNotNull(movie)
        assertEquals(movieEntity, movie)

        viewModel.getDetailMovie(movieEntity.id).observeForever(observer)
        verify(observer).onChanged(movie)
    }

    @Test
    fun checkFavoriteById() {
        val favMovie = MutableLiveData<FavoriteEntity>()
        favMovie.value = DataDummy.getFavoriteMovie("419704")

        `when`(movieRepository.checkFavoriteById(ArgumentMatchers.anyString())).thenReturn(favMovie)
        val movieFavorite = viewModel.checkFavoriteById(ArgumentMatchers.anyString()).value
        verify(movieRepository).checkFavoriteById(ArgumentMatchers.anyString())
        assertNotNull(movieFavorite)
        assertEquals(movieFavorite, favoriteEntity)

        viewModel.checkFavoriteById(ArgumentMatchers.anyString()).observeForever(observerFav)
        verify(observerFav).onChanged(movieFavorite)
    }
}