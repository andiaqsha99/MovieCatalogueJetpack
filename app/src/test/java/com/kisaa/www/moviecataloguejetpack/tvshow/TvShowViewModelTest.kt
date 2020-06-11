package com.kisaa.www.moviecataloguejetpack.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(movieRepository)
    }

    @Test
    fun getListTvShows() {
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = DataDummy.getListTvShows()

        `when`(movieRepository.getListTvShows()).thenReturn(tvShows)
        val listTvShows = viewModel.getListTvShows().value
        verify(movieRepository).getListTvShows()
        assertNotNull(listTvShows)
        assertEquals(3, listTvShows?.size)

        viewModel.getListTvShows().observeForever(observer)
        verify(observer).onChanged(listTvShows)

    }
}