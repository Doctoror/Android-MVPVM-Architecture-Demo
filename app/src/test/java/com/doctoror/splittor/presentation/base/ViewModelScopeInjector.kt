package com.doctoror.splittor.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

private fun ViewModel.replaceViewModelScopeWith(scope: CoroutineScope) {
    ViewModel::class.java
        .getDeclaredField("mBagOfTags")
        .apply { isAccessible = true }
        .get(this)
        .let { it as HashMap<String, CoroutineScope> }
        .run { this["androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY"] = scope }
}

fun ViewModel.runInViewModelScopeBlocking(block: () -> Unit) = runBlocking {
    replaceViewModelScopeWith(this)
    block()
}
