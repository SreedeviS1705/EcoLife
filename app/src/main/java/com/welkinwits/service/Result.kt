package com.welkinwits.service

import com.welkinwits.service.respose.BaseResponse
import retrofit2.Response
import java.lang.ClassCastException
import java.lang.Exception
import java.net.UnknownHostException

/**
 * Generic class for holding success response, error response and loading status
 */
data class Result<out T>(
    val status: Status,
    val data: T?,
    val error: Throwable?,
    val message: String?
) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T?): Result<T> {
            return Result(Status.SUCCESS, data, null, null)
        }

        fun <T> error(message: String?, error: Throwable?): Result<T> {
            return Result(Status.ERROR, null, error, message)
        }

        fun <T> loading(data: T? = null): Result<T> {
            return Result(Status.LOADING, data, null, null)
        }

        suspend fun <T> getResponse(request: suspend () -> Response<T>): Result<T> {
            return try {
                val result = request.invoke()
                val baseResponse = result.body() as BaseResponse
                if (result.isSuccessful) {
                    if (baseResponse.status == true) {
                        success(result.body())
                    } else {
                        if (baseResponse.statusCode == 401) {
                            error("Unauthorized:" + baseResponse.message, null)
                        } else {
                            error(baseResponse.message, null)
                        }

                    }
                } else {
                    error(baseResponse.message, null)
                }
            } catch (e: Throwable) {
                when (e) {
                    is ClassCastException -> {
                        throw Exception("Response class is not extends BaseResponse")
                    }
                    is UnknownHostException -> {
                        error("Unable to connect server. Please try again later", e)
                    }
                    else -> {
                        error("Something went wrong. Please try again later", e)
                    }
                }

            }
        }
    }

    override fun toString(): String {
        return "Result(status=$status, data=$data, error=$error, message=$message)"
    }


}