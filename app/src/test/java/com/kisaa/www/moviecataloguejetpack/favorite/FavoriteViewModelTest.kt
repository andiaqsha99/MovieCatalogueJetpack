package com.kisaa.www.moviecataloguejetpack.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var pagedList: PagedList<FavoriteEntity>

    @Mock
    private lateinit var observer: Observer<PagedList<FavoriteEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyData = pagedList
        `when`(dummyData.size).thenReturn(5)
        val favorite = MutableLiveData<PagedList<FavoriteEntity>>()
        favorite.value = dummyData

        `when`(movieRepository.getFavoriteMovies()).thenReturn(favorite)
        val favoriteMovie = viewModel.getFavoriteMovies().value
        verify(movieRepository).getFavoriteMovies()
        assertNotNull(favoriteMovie)
        assertEquals(5, favoriteMovie?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(favoriteMovie)
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyData = pagedList
        `when`(dummyData.size).thenReturn(5)
        val favorite = MutableLiveData<PagedList<FavoriteEntity>>()
        favorite.value = dummyData

        `when`(movieRepository.getFavoriteTvShows()).thenReturn(favorite)
        val favoriteTvShow = viewModel.getFavoriteTvShows().value
        verify(movieRepository).getFavoriteTvShows()
        assertNotNull(favoriteTvShow)
        assertEquals(5, favoriteTvShow?.size)

        viewModel.getFavoriteTvShows().observeForever(observer)
        verify(observer).onChanged(favoriteTvShow)
    }
}