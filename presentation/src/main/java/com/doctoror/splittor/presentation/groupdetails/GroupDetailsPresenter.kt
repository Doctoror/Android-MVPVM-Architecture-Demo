package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.domain.groups.UpdateMemberPaidStatusUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GroupDetailsPresenter(
    private val groupId: Long,
    private val observeGroupUseCase: ObserveGroupUseCase,
    private val updateMemberPaidStatusUseCase: UpdateMemberPaidStatusUseCase,
    val viewModel: GroupDetailsViewModel,
    private val viewModelScope: CoroutineScope,
    private val viewModelUpdater: GroupDetailsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        viewModelScope.launch {
            observeGroupUseCase(groupId)
                .collect { viewModelUpdater.update(viewModel, it) }
        }
    }

    fun updateMemberPaidStatus(memberId: Long, paid: Boolean) {
        viewModelScope.launch {
            updateMemberPaidStatusUseCase(memberId, paid)
        }
    }
}
