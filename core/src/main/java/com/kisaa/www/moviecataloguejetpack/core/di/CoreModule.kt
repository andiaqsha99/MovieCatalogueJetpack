package com.kisaa.www.moviecataloguejetpack.core.di

import androidx.room.Room
import com.kisaa.www.moviecataloguejetpack.core.BuildConfig
import com.kisaa.www.moviecataloguejetpack.core.data.MovieRepository
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.LocalDataResource
import com.kisaa.www.moviecataloguejetpack.core.data.source.local.room.FavoriteDatabase
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.RemoteDataSource
import com.kisaa.www.moviecataloguejetpack.core.data.source.remote.network.ApiService
import com.kisaa.www.moviecataloguejetpack.core.domain.repository.IMovieRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<FavoriteDatabase>().favoriteDao() }
    factory { get<FavoriteDatabase>().movieDao() }
    factory { get<FavoriteDatabase>().tvShowDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movie_catalogue".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(androidApplication(), FavoriteDatabase::class.java, "Favorite_db")
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()
        val client = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        client.build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataResource(get(), get(), get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get()
        )
    }
}


