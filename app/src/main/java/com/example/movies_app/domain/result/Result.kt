package com.example.movies_app.domain.result

sealed class Result<out R> {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(val value: Exception): Result<Nothing>()
}