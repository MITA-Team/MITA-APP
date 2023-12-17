package com.example.mita.utils

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

@Suppress("UNCHECKED_CAST", "EXPERIMENTAL_API_USAGE")
suspend fun <T> Task<T>.await(): T {
    if (isComplete) {
        val e = exception
        return if (e == null) {
            if (isCanceled) throw CancellationException("Task $this was cancelled normally.")
            else result as T
        } else {
            throw e
        }
    }

    return suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            val e = exception
            if (e == null) {
                if (isCanceled) cont.cancel() else cont.resume(result as T) {}
            } else {
                cont.resumeWithException(e)
            }
        }
    }
}
