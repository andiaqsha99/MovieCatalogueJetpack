package com.kisaa.www.moviecataloguejetpack.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM table_favorite WHERE category = 'movie'")
    fun getFavoriteMovie(): DataSource.Factory<Int, FavoriteEntity>

    @Query("SELECT * FROM table_favorite WHERE category = 'tvShow'")
    fun getFavoriteTvShow(): DataSource.Factory<Int, FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFromFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM table_favorite WHERE id = :id")
    fun checkFavoriteById(id: String): LiveData<FavoriteEntity>
}