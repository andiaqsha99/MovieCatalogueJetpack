package com.kisaa.www.moviecataloguejetpack.core.data.source.local.room

import androidx.room.*
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM table_favorite WHERE category = 'movie'")
    fun getListFavoriteMovie(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM table_favorite WHERE category = 'tvShow'")
    fun getListFavoriteTvShow(): Flow<List<FavoriteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(favorite: FavoriteEntity)

    @Delete
    suspend fun deleteFromFavorite(favorite: FavoriteEntity)

    @Query("SELECT * FROM table_favorite WHERE id = :id")
    fun checkFavoriteById(id: String): Flow<FavoriteEntity?>
}