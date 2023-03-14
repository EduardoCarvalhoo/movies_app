package com.example.movies_app.di

import com.example.movies_app.data.remote.movie.dataSource.MovieApiDataSource
import com.example.movies_app.data.remote.movie.dataSource.MovieApiDataSourceImpl
import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.data.repository.MoviesRepositoryImpl
import com.example.movies_app.data.remote.rest.MovieApiService
import com.example.movies_app.domain.useCase.GetMovieList
import com.example.movies_app.domain.useCase.GetMovieListImpl
import com.example.movies_app.presentation.home.HomeViewModel
import com.example.movies_app.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<MovieApiService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApiService::class.java)
    }
}

val homeModule = module {
    single<GetMovieList> { GetMovieListImpl(moviesRepository = get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(movieApiDataSource = get()) }
    single<MovieApiDataSource> { MovieApiDataSourceImpl(service = get()) }
    viewModel { HomeViewModel(getMovieList = get()) }
}
