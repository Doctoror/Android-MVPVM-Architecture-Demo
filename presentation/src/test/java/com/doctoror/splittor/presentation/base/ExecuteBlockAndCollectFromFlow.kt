package com.doctoror.splittor.presentation.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

/**
 * Subscribes to [flow] asynchronously, then executes the [block] and cancels flow subscription job.
 *
 * @return emissions collected from [flow]
 */
@OptIn(ExperimentalCoroutinesApi::class)
fun <T> executeBlockAndCollectFromFlow(
    flow: Flow<T>,
    block: () -> Unit
): List<T> {
    val collected = mutableListOf<T>()
    runTest(UnconfinedTestDispatcher()) {
        val collectJob = launch {
            flow.collect(collected::add)
        }

        block()
        collectJob.cancel()
    }
    return collected
}
