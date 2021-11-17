package com.doctoror.splittor.presentation.groups

import com.doctoror.splittor.domain.groups.GetGroupsUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import io.reactivex.rxjava3.core.Scheduler

class GroupsPresenter(
    private val getGroupsUseCase: GetGroupsUseCase,
    private val schedulerIo: Scheduler,
    private val schedulerMainThread: Scheduler,
    private val viewModelUpdater: GroupsViewModelUpdater
) : BasePresenter() {

    override fun onCreate() {
        getGroupsUseCase
            .observeGroups()
            .subscribeOn(schedulerIo)
            .observeOn(schedulerMainThread)
            .subscribe(viewModelUpdater::updateOnGroupsListLoaded)
            .disposeOnDestroy()
    }
}
