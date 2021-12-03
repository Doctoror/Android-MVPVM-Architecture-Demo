package com.doctoror.splittor.presentation.groupdetails

import androidx.lifecycle.viewModelScope
import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import io.reactivex.rxjava3.core.Scheduler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GroupDetailsPresenter(
    private val dispatcherIo: CoroutineDispatcher,
    private val groupId: Long,
    private val observeGroupUseCase: ObserveGroupUseCase,
    private val schedulerIo: Scheduler,
    private val updateMemberPaidStatusUseCase: UpdateMemberPaidStatusUseCase,
    private val viewModelUpdater: GroupDetailsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        viewModelScope.launch {
            observeGroupUseCase
                .observe(groupId)
                .flowOn(dispatcherIo)
                .collect { viewModelUpdater.update(it) }
        }
    }

    fun updateMemberPaidStatus(memberId: Long, paid: Boolean) {
        updateMemberPaidStatusUseCase
            .updateMemberPaidStatus(memberId, paid)
            .subscribeOn(schedulerIo)
            .subscribe()
            .disposeOnDestroy()
    }
}
