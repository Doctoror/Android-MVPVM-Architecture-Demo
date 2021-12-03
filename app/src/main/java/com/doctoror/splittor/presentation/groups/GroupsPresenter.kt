package com.doctoror.splittor.presentation.groups

import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GroupsPresenter(
    private val dispatcherIo: CoroutineDispatcher,
    private val observeGroupsUseCase: ObserveGroupsUseCase,
    private val viewModelUpdater: GroupsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        viewModelScope.launch {
            observeGroupsUseCase
                .observe()
                .flowOn(dispatcherIo)
                .collect { viewModelUpdater.updateOnGroupsListLoaded(it) }
        }
    }
}
