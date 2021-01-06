package com.kisaa.www.moviecataloguejetpack.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.FavoriteEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.MovieEntity
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.entity.TvShowEntity

@Database(
    entities = [FavoriteEntity::class, MovieEntity::class, TvShowEntity::class],
    version = 1,
    exportSchema = false
)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun tvShowDao(): TvShowDao
    abstract fun movieDao(): MovieDao
}