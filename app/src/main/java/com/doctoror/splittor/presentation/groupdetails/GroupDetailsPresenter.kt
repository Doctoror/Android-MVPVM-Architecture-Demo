package com.doctoror.splittor.presentation.groupdetails

import com.doctoror.splittor.domain.groups.ObserveGroupUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import io.reactivex.rxjava3.core.Scheduler

class GroupDetailsPresenter(
    private val groupId: Long,
    private val observeGroupUseCase: ObserveGroupUseCase,
    private val schedulerIo: Scheduler,
    private val schedulerMainThread: Scheduler,
    private val viewModelUpdater: GroupDetailsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        observeGroupUseCase
            .observe(groupId)
            .subscribeOn(schedulerIo)
            .observeOn(schedulerMainThread)
            .subscribe(viewModelUpdater::update)
            .disposeOnDestroy()
    }
}
