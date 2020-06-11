package com.kisaa.www.moviecataloguejetpack.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.kisaa.www.moviecataloguejetpack.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
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
class TvShowDetailViewModelTest {

    private lateinit var viewModel: TvShowDetailViewModel
    private lateinit var tvShowEntity: TvShowEntity
    private lateinit var favoriteEntity: FavoriteEntity

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<TvShowEntity>

    @Mock
    private lateinit var observerFav: Observer<FavoriteEntity>

    @Before
    fun setUp() {
        viewModel = TvShowDetailViewModel(movieRepository)
        tvShowEntity =
            TvShowEntity()
        favoriteEntity = DataDummy.getFavoriteTvShow("1402")!!
    }

    @Test
    fun getTvShowDetail() {
        val dataTvShow = MutableLiveData<TvShowEntity>()
        dataTvShow.value = DataDummy.getTvShow(tvShowEntity.id)

        `when`(movieRepository.getDetailTvShow(tvShowEntity.id)).thenReturn(dataTvShow)
        val tvShow = viewModel.getTvShowDetail(tvShowEntity.id).value
        verify(movieRepository).getDetailTvShow(tvShowEntity.id)
        assertNotNull(tvShow)
        assertEquals(tvShowEntity, tvShow)

        viewModel.getTvShowDetail(tvShowEntity.id).observeForever(observer)
        verify(observer).onChanged(tvShow)
    }

    @Test
    fun checkFavoriteById() {
        val favTvShow = MutableLiveData<FavoriteEntity>()
        favTvShow.value = DataDummy.getFavoriteTvShow("1402")

        `when`(movieRepository.checkFavoriteById(ArgumentMatchers.anyString())).thenReturn(favTvShow)
        val favoriteTvShow = viewModel.checkFavoriteById(ArgumentMatchers.anyString()).value
        verify(movieRepository).checkFavoriteById(ArgumentMatchers.anyString())
        assertNotNull(favoriteTvShow)
        assertEquals(favoriteTvShow, favoriteEntity)

        viewModel.checkFavoriteById(ArgumentMatchers.anyString()).observeForever(observerFav)
        verify(observerFav).onChanged(favoriteTvShow)
    }
}