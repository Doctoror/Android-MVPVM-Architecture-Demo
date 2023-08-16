package com.doctoror.splittor.presentation.groupdetails

import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GroupDetailsPresenter(
    private val dispatcherIo: CoroutineDispatcher,
    private val groupId: Long,
    private val observeGroupUseCase: ObserveGroupUseCase,
    private val updateMemberPaidStatusUseCase: UpdateMemberPaidStatusUseCase,
    private val viewModelUpdater: GroupDetailsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        viewModelScope.launch {
            observeGroupUseCase(groupId)
                .flowOn(dispatcherIo)
                .collect { viewModelUpdater.update(it) }
        }
    }

    fun updateMemberPaidStatus(memberId: Long, paid: Boolean) {
        viewModelScope.launch {
            updateMemberPaidStatusUseCase(memberId, paid)
        }
    }
}
