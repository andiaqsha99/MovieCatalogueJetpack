package com.kisaa.www.moviecataloguejetpack.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteDao
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MoviesResponse
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowsResponse
import com.kisaa.www.moviecataloguejetpack.network.ApiService
import com.kisaa.www.moviecataloguejetpack.utils.DataDummy
import com.kisaa.www.moviecataloguejetpack.utils.LiveDataTestUtil
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var favoriteDao: FavoriteDao

    private lateinit var repository: MovieRepository
    private lateinit var favoriteEntity: FavoriteEntity

    @Before
    fun setUp() {
        repository = MovieRepository(apiService, favoriteDao)
        favoriteEntity = DataDummy.getFavoriteMovie("419704")!!
    }

    @Test
    fun getListMovies() {
        val movies =
            MoviesResponse(
                DataDummy.getListMovies()
            )

        `when`(apiService.getMovies()).thenReturn(Observable.just(movies))
        val data = LiveDataTestUtil.getValue(repository.getListMovies())
        verify(apiService).getMovies()
        assertNotNull(data)
        assertEquals(movies.results?.size, data.size)
    }

    @Test
    fun getListTvShows() {
        val tvShows =
            TvShowsResponse(
                DataDummy.getListTvShows()
            )

        `when`(apiService.getTvShows()).thenReturn(Observable.just(tvShows))
        val data = LiveDataTestUtil.getValue(repository.getListTvShows())
        verify(apiService).getTvShows()
        assertNotNull(data)
        assertEquals(tvShows.results?.size, data.size)
    }

    @Test
    fun getDetailMovie() {
        val id = "419704"
        val movies = DataDummy.getMovie(id)

        `when`(apiService.getDetailMovie(id)).thenReturn(Observable.just(movies))
        val data = LiveDataTestUtil.getValue(repository.getDetailMovie(id))
        verify(apiService).getDetailMovie(id)

        assertNotNull(data)
        assertEquals(movies.title, data.title)
    }

    @Test
    fun getDetailTvShow() {
        val id = "1402"
        val tvShow = DataDummy.getTvShow(id)

        `when`(apiService.getDetailTvShow(id)).thenReturn(Observable.just(tvShow))
        val data = LiveDataTestUtil.getValue(repository.getDetailTvShow(id))
        verify(apiService).getDetailTvShow(id)

        assertNotNull(data)
        assertEquals(tvShow.name, data.name)
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavoriteMovie()).thenReturn(dataSourceFactory)
        repository.getFavoriteMovies()
        val favMovies = PagedListUtil.mockPagedList(DataDummy.getListFavoriteMovies())
        verify(favoriteDao).getFavoriteMovie()
        assertNotNull(favMovies)
        assertEquals(3, favMovies.size)
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, FavoriteEntity>
        `when`(favoriteDao.getFavoriteTvShow()).thenReturn(dataSourceFactory)
        repository.getFavoriteTvShows()
        val favMovies = PagedListUtil.mockPagedList(DataDummy.getListFavoriteTvShows())
        verify(favoriteDao).getFavoriteTvShow()
        assertNotNull(favMovies)
        assertEquals(3, favMovies.size)
    }

    @Test
    fun checkFavoriteById() {
        val favMovie = MutableLiveData<FavoriteEntity>()
        favMovie.value = DataDummy.getFavoriteMovie("419704")

        `when`(favoriteDao.checkFavoriteById(ArgumentMatchers.anyString())).thenReturn(favMovie)
        val movieFavorite = repository.checkFavoriteById(ArgumentMatchers.anyString()).value
        verify(favoriteDao).checkFavoriteById(ArgumentMatchers.anyString())
        assertNotNull(movieFavorite)
        assertEquals(movieFavorite, favoriteEntity)
    }
}