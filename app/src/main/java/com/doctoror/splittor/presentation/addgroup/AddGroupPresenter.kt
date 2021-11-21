package com.doctoror.splittor.presentation.addgroup

import android.net.Uri
import com.doctoror.splittor.domain.contacts.ContactDetailsResolver
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.presentation.base.BasePresenter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.subjects.PublishSubject

class AddGroupPresenter(
    private val contactDetailsResolver: ContactDetailsResolver,
    private val contactPickedEvents: Observable<Uri>,
    private val inputFieldsMonitor: AddGroupInputFieldsMonitor,
    private val insertGroupUseCase: InsertGroupUseCase,
    private val schedulerIo: Scheduler,
    private val schedulerMainThread: Scheduler,
    private val viewModelUpdater: AddGroupViewModelUpdater
) : BasePresenter() {

    private val groupInsertedEventsSubject = PublishSubject.create<Long>()
    val groupInsertedEvents: Observable<Long> = groupInsertedEventsSubject

    override fun onCreate() {
        contactPickedEvents
            .observeOn(schedulerIo)
            .map(contactDetailsResolver::resolve)
            .observeOn(schedulerMainThread)
            .subscribe {
                if (it.isPresent) {
                    inputFieldsMonitor.contacts.add(it.get())
                    viewModelUpdater.addContact(it.get())
                }
            }
            .disposeOnDestroy()
    }

    fun createGroup() {
        insertGroupUseCase
            .insert()
            .observeOn(schedulerMainThread)
            .subscribe(groupInsertedEventsSubject::onNext)
            .disposeOnDestroy()
    }
}
