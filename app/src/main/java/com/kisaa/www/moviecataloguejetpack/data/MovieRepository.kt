package com.kisaa.www.moviecataloguejetpack.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteDao
import com.kisaa.www.moviecataloguejetpack.data.source.local.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MovieEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.MoviesResponse
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowEntity
import com.kisaa.www.moviecataloguejetpack.data.source.remote.TvShowsResponse
import com.kisaa.www.moviecataloguejetpack.network.ApiService
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieRepository(private val apiService: ApiService, private val favoriteDao: FavoriteDao) {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(5)
        .setPageSize(5)
        .build()

    fun getListMovies(): LiveData<List<MovieEntity>> {
        val listMovies = MutableLiveData<List<MovieEntity>>()

        apiService.getMovies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<MoviesResponse> {
                override fun onNext(t: MoviesResponse) {
                    listMovies.postValue(t.results)
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
        return listMovies
    }

    fun getListTvShows(): LiveData<List<TvShowEntity>> {
        val listTvShows = MutableLiveData<List<TvShowEntity>>()

        apiService.getTvShows()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<TvShowsResponse> {
                override fun onNext(t: TvShowsResponse) {
                    listTvShows.postValue(t.results)
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }
            })
        return listTvShows
    }

    fun getDetailMovie(movieId: String?): LiveData<MovieEntity> {
        val detailMovie = MutableLiveData<MovieEntity>()

        apiService.getDetailMovie(movieId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<MovieEntity> {
                override fun onNext(t: MovieEntity) {
                    detailMovie.postValue(t)
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
        return detailMovie
    }

    fun getDetailTvShow(tvShowId: String?): LiveData<TvShowEntity> {
        val detailTvShow = MutableLiveData<TvShowEntity>()

        apiService.getDetailTvShow(tvShowId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<TvShowEntity> {
                override fun onNext(t: TvShowEntity) {
                    detailTvShow.postValue(t)
                }

                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
        return detailTvShow
    }

    fun getFavoriteMovies(): LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(favoriteDao.getFavoriteMovie(), config).build()
    }

    fun getFavoriteTvShows(): LiveData<PagedList<FavoriteEntity>> {
        return LivePagedListBuilder(favoriteDao.getFavoriteTvShow(), config).build()
    }

    fun checkFavoriteById(id: String): LiveData<FavoriteEntity> {
        return favoriteDao.checkFavoriteById(id)
    }

    suspend fun insertToFavorite(fav: FavoriteEntity) {
        favoriteDao.addToFavorite(fav)
    }

    suspend fun deleteFromFavorite(fav: FavoriteEntity) {
        favoriteDao.deleteFromFavorite(fav)
    }
}
