package com.example.movies_app.data.remote.rest

import com.example.movies_app.domain.result.Result
import com.example.movies_app.data.exception.GenericErrorException
import com.example.movies_app.data.exception.NetworkErrorException
import com.example.movies_app.data.exception.ServerErrorException
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> retrofitWrapper(call: suspend () -> T): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (ioException: IOException) {
        Result.Error(NetworkErrorException())
    } catch (httpException: HttpException) {
        Result.Error(ServerErrorException())
    } catch (throwable: Throwable) {
        Result.Error(GenericErrorException())
    }
}