package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.domain.groups.ObserveGroupsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import io.reactivex.rxjava3.core.Scheduler

class GroupsPresenter(
    private val observeGroupsUseCase: ObserveGroupsUseCase,
    private val schedulerIo: Scheduler,
    private val schedulerMainThread: Scheduler,
    private val viewModelUpdater: GroupsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        observeGroupsUseCase
            .observe()
            .subscribeOn(schedulerIo)
            .observeOn(schedulerMainThread)
            .subscribe(viewModelUpdater::updateOnGroupsListLoaded)
            .disposeOnDestroy()
    }
}
