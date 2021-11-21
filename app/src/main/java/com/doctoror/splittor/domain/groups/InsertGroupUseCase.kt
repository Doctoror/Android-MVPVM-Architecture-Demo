package com.doctoror.splittor.domain.groups

import com.doctoror.splittor.presentation.addgroup.AddGroupInputFieldsMonitor
import com.doctoror.splittor.presentation.addgroup.AddGroupInputFieldsValidator
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModelUpdater
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single

class InsertGroupUseCase(
    private val groupsRepository: GroupsRepository,
    private val inputFieldsMonitor: AddGroupInputFieldsMonitor,
    private val inputFieldsValidator: AddGroupInputFieldsValidator,
    private val schedulerIo: Scheduler,
    private val schedulerMainThread: Scheduler,
    private val viewModelUpdater: AddGroupViewModelUpdater
) {

    fun insert(): Maybe<Boolean> = Single
        .fromCallable { inputFieldsValidator.validate() }
        .subscribeOn(schedulerMainThread)
        .doOnSuccess {
            if (!it.valid) {
                viewModelUpdater.setErrorMessageId(it.messageId)
            }
        }
        .filter { it.valid }
        .observeOn(schedulerIo)
        .flatMapSingle {
            groupsRepository
                .insert(
                    contacts = inputFieldsMonitor.contacts.toList(),
                    amount = inputFieldsMonitor.amount!!.toString(),
                    title = inputFieldsMonitor.title!!.toString()
                )
                .andThen(Single.just(true))
        }
}
