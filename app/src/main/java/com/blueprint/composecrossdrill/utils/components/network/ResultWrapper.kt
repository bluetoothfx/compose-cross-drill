package com.blueprint.composecrossdrill.utils.components.network

sealed class ResultWrapper<out T> {
    data object Loading : ResultWrapper<Nothing>()
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Failure(
        val throwable: Throwable?,
        val message: String? = throwable?.localizedMessage
    ) : ResultWrapper<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success(data=$data)"
            is Failure -> "Failure(message=${message ?: "Unknown error"}, throwable=${throwable?.javaClass?.simpleName})"
            Loading -> "Loading"
        }
    }

    /** Maps Success<T> to Success<R> while keeping Failure and Loading unchanged */
    inline fun <R> map(transform: (T) -> R): ResultWrapper<R> {
        return when (this) {
            is Success -> Success(transform(data))
            is Failure -> this
            Loading -> Loading
        }
    }

    /** Extracts data if Success, otherwise returns null */
    fun getOrNull(): T? = (this as? Success)?.data
}
