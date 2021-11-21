package com.doctoror.splittor.presentation.addgroup

import android.net.Uri
import com.doctoror.splittor.R
import com.doctoror.splittor.domain.contacts.ContactDetailsResolver
import com.doctoror.splittor.domain.groups.InsertGroupUseCase
import com.doctoror.splittor.domain.groups.ValidateAddGroupInputFieldsUseCase
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
    private val validateAddGroupInputFieldsUseCase: ValidateAddGroupInputFieldsUseCase,
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
        validateAddGroupInputFieldsUseCase
            .validate(
                inputFieldsMonitor.amount,
                inputFieldsMonitor.contacts,
                inputFieldsMonitor.title
            )
            .doOnSuccess {
                viewModelUpdater.setErrorMessageId(
                    when (it) {
                        ValidateAddGroupInputFieldsUseCase.ValidationResult.AMOUNT_MISSING ->
                            R.string.amount_not_set

                        ValidateAddGroupInputFieldsUseCase.ValidationResult.CONTACTS_MISSING ->
                            R.string.no_contacts_added

                        ValidateAddGroupInputFieldsUseCase.ValidationResult.TITLE_MISSING ->
                            R.string.title_not_set

                        else -> 0
                    }
                )
            }
            .filter { it == ValidateAddGroupInputFieldsUseCase.ValidationResult.VALID }
            .flatMapSingle {
                insertGroupUseCase
                    .insert(
                        inputFieldsMonitor.amount!!.toString(),
                        inputFieldsMonitor.contacts.toList(),
                        inputFieldsMonitor.title!!.toString()
                    )
                    .subscribeOn(schedulerIo)
            }
            .observeOn(schedulerMainThread)
            .subscribe(groupInsertedEventsSubject::onNext)
            .disposeOnDestroy()
    }
}
