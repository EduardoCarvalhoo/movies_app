package com.example.movies_app.di

import com.example.movies_app.data.repository.MoviesRepository
import com.example.movies_app.data.rest.api.MoviesRepositoryImpl
import com.example.movies_app.data.rest.service.MovieService
import com.example.movies_app.presentation.ui.home.HomeViewModel
import com.example.movies_app.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<MovieService> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }
}

val homeModule = module {
    single<MoviesRepository> { MoviesRepositoryImpl(service = get()) }
    viewModel { HomeViewModel(moviesRepository = get()) }
}
